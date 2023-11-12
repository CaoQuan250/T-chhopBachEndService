package com.example.backEndService.sercurity;
import com.example.backEndService.entities.Users;
import com.example.backEndService.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UsersRepository usersRepository;
    @Override
    public UserDetails loadUserByUsername(String username) {
        Users user = usersRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("`No user found with username: "+username));
        return new CustomUserDetails(user);
    }

    public UserDetails findById(Long id) {
        Users user = usersRepository.findById(id).orElseThrow(() ->
                new RuntimeException("No user found"));
        return new CustomUserDetails(user);
    }
}
