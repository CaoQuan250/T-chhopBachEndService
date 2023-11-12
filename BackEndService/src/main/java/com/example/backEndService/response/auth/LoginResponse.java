package com.example.backEndService.response.auth;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private String type = "Bearer ";
    public LoginResponse(String token){
        this.token = token;
    }
}
