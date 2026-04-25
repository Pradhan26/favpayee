package com.bankapplication.auth.service;

import com.bankapplication.auth.models.Customer;
import com.bankapplication.auth.models.response.AuthResponse;
import com.bankapplication.auth.repository.CustomerRepository;
import com.bankapplication.auth.security.JWTUtil;
import com.bankapplication.auth.models.request.LoginRequest;
import com.bankapplication.exception.AuthException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;

    public CustomerService(CustomerRepository repository,
                           PasswordEncoder passwordEncoder,
                           JWTUtil jwtUtil) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public AuthResponse login(LoginRequest request) {

        return repository
                .findByCustomerId(request.getCustomerId())

                .filter(customer ->
                        passwordEncoder.matches(
                                request.getPassword(),
                                customer.getPassword()
                        )
                )

                .map(customer ->
                        new AuthResponse(
                                customer.getCustomerId(),
                                jwtUtil.generateToken(customer.getCustomerId()),
                                customer.getName()
                        )
                )

                .orElseThrow(() ->
                        new AuthException("Invalid credentials")
                );
    }

    public Customer getCustomerById(String id){
       return repository.findByCustomerId(id)
                .orElseThrow(() -> new AuthException("Customer not found"));
    }
}