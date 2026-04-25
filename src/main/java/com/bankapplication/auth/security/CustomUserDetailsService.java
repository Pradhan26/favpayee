package com.bankapplication.auth.security;

import com.bankapplication.auth.entity.Customer;
import com.bankapplication.auth.repository.CustomerRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final CustomerRepository repository;

    public CustomUserDetailsService(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String customerId)
            throws UsernameNotFoundException {

        Customer customer = repository.findByCustomerId(customerId)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Customer not found")
                );

        return new org.springframework.security.core.userdetails.User(
                customer.getCustomerId(),
                customer.getPassword(),
                Collections.emptyList()
        );
    }
}