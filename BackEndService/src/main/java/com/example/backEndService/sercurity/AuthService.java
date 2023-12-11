package com.example.backEndService.sercurity;

import com.example.backEndService.base.BaseResponse;
import com.example.backEndService.base.NoDataBaseResponse;
import com.example.backEndService.common.CommonService;
import com.example.backEndService.common.Constants;
import com.example.backEndService.common.Validation;
import com.example.backEndService.dto.TokenInfo;
import com.example.backEndService.entities.*;
import com.example.backEndService.exception.ApplicationException;
import com.example.backEndService.exception.ERROR;
import com.example.backEndService.repository.*;
import com.example.backEndService.request.auth.LoginRequest;
import com.example.backEndService.request.auth.RegisterRequest;
import com.example.backEndService.response.auth.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Scope("request")
public class AuthService {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UsersRolesRepository usersRolesRepository;
    @Autowired
    private CommonService commonService;
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenService jwtTokenService;
    private RegisterRequest form;
    private Users users;

    public NoDataBaseResponse register(RegisterRequest request){
        this.form = request;
        validateRegister();
        populateNewUser();
        populateNewCustomer();
        return new NoDataBaseResponse();
    }

    private void validateRegister(){
        if (usersRepository.existsByUsername(form.getUsername())){
            throw new ApplicationException(ERROR.USERNAME_ALREADY_EXIST);
        }
        if (!Validation.emailValid(form.getEmail())){
            throw new ApplicationException(ERROR.INVALID_EMAIL);
        }
        if (customerRepository.existsByEmail(form.getEmail())){
            throw new ApplicationException(ERROR.EMAIL_ALREADY_EXIST);
        }
        if (!Validation.mobileValid(form.getMobile())){
            throw new ApplicationException(ERROR.INVALID_MOBILE);
        }
    }

    private void populateNewUser(){
        users = new Users();
        users.setUsername(form.getUsername());
        users.setPassword(passwordEncoder.encode(form.getPassword()));
        usersRepository.save(users);
        Roles roles = commonService.getDefaultRole();

        UsersRoles usersRoles = new UsersRoles();

        usersRoles.setUserId(users.getId());
        usersRoles.setRoleId(roles.getId());

        usersRolesRepository.save(usersRoles);
    }

    private void populateNewCustomer(){
        Customer customer = new Customer();
        customer.setUsersId(users.getId());
        customer.setEmail(form.getEmail());
        customer.setMobile(form.getMobile());
        customer.setBirth(form.getBirth());
        customer.setStatus(Constants.CUSTOMER_STATUS.INACTIVE);
        customerRepository.save(customer);
        sendMail(customer);
    }
    private void sendMail(Customer customer){
        String link = "http://localhost:8080/api/auth/activate/"+customer.getId();
        commonService.sendMail(customer.getEmail(),commonService.mailTemplateBuilder(link));
    }
    public BaseResponse<LoginResponse> login(LoginRequest request){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        Users user = usersRepository.findByUsername(authentication.getName()).orElseThrow(() ->
                new UsernameNotFoundException("No user found with username: "+authentication.getName()));
        List<Roles> roles = commonService.getAllRole(user.getId());
        Customer customer = customerRepository.findByUsersId(user.getId());
        if (customer.getStatus().equals(Constants.CUSTOMER_STATUS.INACTIVE)){
            sendMail(customer);
            throw new ApplicationException(ERROR.ACCOUNT_INACTIVE);
        }
        ShoppingCart cart = shoppingCartRepository.findByCustomerId(customer.getId()).get();
        TokenInfo info = new TokenInfo();
        info.setUserId(user.getId());
        info.setUsername(user.getUsername());
        info.setRoles(roles);
        info.setCustomerId(customer.getId());
        info.setEmail(customer.getEmail());
        info.setMobile(customer.getMobile());
        info.setCardId(cart.getId());
        String token = jwtTokenService.genToken(info);
        LoginResponse response = new LoginResponse(token);
        return new BaseResponse<>(response);
    }

    public NoDataBaseResponse accountActivate(Long customerId){
        Customer customer = customerRepository.findById(customerId).orElseThrow(() ->
                new RuntimeException("Cant find customer"));
        customer.setStatus(Constants.CUSTOMER_STATUS.ACTIVE);
        customerRepository.save(customer);
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setCustomerId(customerId);
        shoppingCartRepository.save(shoppingCart);
        return new NoDataBaseResponse();
    }

}
