package com.nawanolja.backend.module.auth.controller;

import com.nawanolja.backend.core.dto.ApiResponse;
import com.nawanolja.backend.module.auth.application.LoginService;
import com.nawanolja.backend.module.auth.application.SignupService;
import com.nawanolja.backend.module.auth.controller.dto.SignupRequest;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private LoginService loginService;
    private SignupService signupService;

    @PostMapping("/login")
    public ApiResponse<Void> login(){
        return ApiResponse.of(HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ApiResponse<Void> signup(@RequestBody @Valid SignupRequest request){
        return ApiResponse.created();
    }

    @GetMapping("/test")
    public String test(){
        return "success";
    }

}
