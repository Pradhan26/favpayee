package com.bankapplication.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bankapplication.auth.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer ,String> {

 Optional<Customer> findByCustomerId(String customerId);
}