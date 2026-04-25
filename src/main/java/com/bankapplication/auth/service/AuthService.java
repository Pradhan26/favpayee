package com.bankapplication.auth.service;

import com.bankapplication.auth.models.response.AuthResponse;
import com.bankapplication.auth.models.request.CustomerLoginRequest;
import com.bankapplication.auth.models.Customer;
import com.bankapplication.auth.repository.CustomerRepository;
import com.demo.authservice.security.JWTUtil;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final CustomerRepository repository;

    private final PasswordEncoder passwordEncoder;

    private final JWTUtil jwtUtil;


    public AuthResponse login(
            CustomerLoginRequest request){

        return repository
            .findByCustomerId(
                request.getCustomerId()
            )

            .filter(customer ->
                passwordEncoder.matches(
                    request.getPassword(),
                    customer.getPassword()
                )
            )

            .map(customer ->
                AuthResponse.builder()
                    .customerId(
                       customer.getCustomerId()
                    )
                    .token(
                       jwtUtil.generateToken(
                           customer.getCustomerId()
                       )
                    )
                    .build()
            )

            .orElseThrow(() ->
                new AuthException(
                    "Invalid credentials"
                )
            );
    }

}
