package com.bankapplication.bank.service;

import com.bankapplication.auth.models.Customer;
import com.bankapplication.auth.models.request.LoginRequest;
import com.bankapplication.auth.models.response.AuthResponse;
import com.bankapplication.auth.repository.CustomerRepository;
import com.bankapplication.auth.security.JWTUtil;
import com.bankapplication.auth.service.CustomerService;
import com.bankapplication.exception.AuthException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository repository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private JWTUtil jwtUtil;
    private CustomerService CustomerService;

    private Customer customer;
    private LoginRequest request;

    @BeforeEach
    void setup() {
        jwtUtil = new JWTUtil();
        CustomerService = new CustomerService(repository, passwordEncoder, jwtUtil);

        customer = new Customer();
        customer.setCustomerId("CUST1001");
        customer.setName("Professor");
        customer.setPassword("encodedPassword");
        customer.setEnabled(true);

        request = new LoginRequest("CUST1001", "secret123");
    }

    @Test
    void loginSuccess() {
        when(repository.findByCustomerId("CUST1001"))
                .thenReturn(Optional.of(customer));

        when(passwordEncoder.matches("secret123", "encodedPassword"))
                .thenReturn(true);

        AuthResponse response = CustomerService.login(request);

        assertNotNull(response);
        assertEquals("CUST1001", response.getCustomerId());
        assertNotNull(response.getToken());

        verify(repository).findByCustomerId("CUST1001");
        verify(passwordEncoder).matches("secret123", "encodedPassword");
    }

    @Test
    void invalidPassword() {
        when(repository.findByCustomerId("CUST1001"))
                .thenReturn(Optional.of(customer));

        when(passwordEncoder.matches(any(), any()))
                .thenReturn(false);

        assertThrows(AuthException.class,
                () -> CustomerService.login(request));
    }

    @Test
    void customerNotFound() {
        when(repository.findByCustomerId("CUST1001"))
                .thenReturn(Optional.empty());

        assertThrows(AuthException.class,
                () -> CustomerService.login(request));

        verify(passwordEncoder, never()).matches(any(), any());
    }

    @Test
    void tokenGenerated() {
        when(repository.findByCustomerId("CUST1001"))
                .thenReturn(Optional.of(customer));

        when(passwordEncoder.matches(any(), any()))
                .thenReturn(true);

        AuthResponse response = CustomerService.login(request);

        assertNotNull(response.getToken());
    }
}