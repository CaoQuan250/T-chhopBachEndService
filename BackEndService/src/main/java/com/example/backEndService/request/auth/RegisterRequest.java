package com.example.backEndService.request.auth;

import lombok.Data;

import java.util.Date;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private String email;
    private String mobile;
    private Date birth;
}
