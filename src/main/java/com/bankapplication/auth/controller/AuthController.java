package com.bankapplication.auth.controller;
import com.bankapplication.auth.models.request.CustomerLoginRequest;

import com.bankapplication.auth.models.response.AuthResponse;
import com.bankapplication.auth.service.AuthService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
        @RequestBody CustomerLoginRequest request){

        AuthResponse response =
            authService.login(request);

        return ResponseEntity.ok(response);
    }

}
