package com.example.backEndService.controller;

import com.example.backEndService.base.BaseController;
import com.example.backEndService.base.BaseResponse;
import com.example.backEndService.base.NoDataBaseResponse;
import com.example.backEndService.exception.ApplicationException;
import com.example.backEndService.request.auth.LoginRequest;
import com.example.backEndService.request.auth.RegisterRequest;
import com.example.backEndService.response.auth.LoginResponse;
import com.example.backEndService.sercurity.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Scope("request")
public class SecurityController extends BaseController {
    @Autowired
    private AuthService authService;
    @PostMapping("/register")
    public ResponseEntity<NoDataBaseResponse> register(@RequestBody RegisterRequest request) throws ApplicationException {
        return createNoDataResponse(authService.register(request));
    }
    @PostMapping("/login")
    public ResponseEntity<BaseResponse<LoginResponse>> login(@RequestBody LoginRequest request) throws ApplicationException{
        return createResponse(authService.login(request));
    }
    @PostMapping("/activate/{id}")
    public ResponseEntity<NoDataBaseResponse> activate(@PathVariable Long id) throws ApplicationException{
        return createNoDataResponse(authService.accountActivate(id));
    }
}
