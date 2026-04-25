package com.bankapplication.auth.controller;

import com.bankapplication.auth.models.response.AuthResponse;
import com.bankapplication.auth.service.CustomerService;

import com.bankapplication.auth.models.request.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final CustomerService customerService;

    public AuthController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
        @RequestBody LoginRequest request){

        AuthResponse response =
            customerService.login(request);

        return ResponseEntity.ok(response);
    }

}
