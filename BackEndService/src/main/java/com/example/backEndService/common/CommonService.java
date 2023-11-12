package com.example.backEndService.common;

import com.example.backEndService.entities.Roles;
import com.example.backEndService.entities.UsersRoles;
import com.example.backEndService.repository.RolesRepository;
import com.example.backEndService.repository.UsersRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommonService {
    @Autowired
    private UsersRolesRepository usersRolesRepository;
    @Autowired
    private RolesRepository rolesRepository;
    @Autowired
    private JavaMailSender javaMailSender;

    public List<Roles> getAllRole(Long id){
        List<UsersRoles> usersRolesList = usersRolesRepository.findAllByUserId(id);
        List<Long> listId = usersRolesList.stream()
                .map(UsersRoles::getRoleId)
                .collect(Collectors.toList());
        return rolesRepository.findAllByIdIn(listId);
    }

    public Roles getDefaultRole() {
        return rolesRepository.findByName(Constants.ROLE.CUSTOMER).orElseThrow(() ->
                new RuntimeException("The role "+Constants.ROLE.CUSTOMER+" have not been initialize"));
    }

    public void createBaseRole(){
        if (!rolesRepository.existsByName(Constants.ROLE.CUSTOMER)){
            createRole(Constants.ROLE.CUSTOMER);
        }

        if (!rolesRepository.existsByName(Constants.ROLE.ADMIN)){
            createRole(Constants.ROLE.ADMIN);
        }
    }
    private void createRole(String name){
        Roles roles = new Roles();
        roles.setName(name);
        rolesRepository.save(roles);
    }
    public void sendMail(String to,String text){
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(to);
            helper.setSubject("Account confirm");
            helper.setText(text, true);
            helper.setFrom("TechShop");
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public String mailTemplateBuilder(String link){
        return  "<p>Welcome to our service</p>" +
                "<p>Thank you for registering. To activate your account, please click the button below:</p>" +
                "<a href=\"" + link + "\"><button style=\"background-color: #007BFF; color: #ffffff; padding: 10px 20px; text-decoration: none; border-radius: 5px; font-weight: bold;\">Activate Account</button></a>";

    }

}
