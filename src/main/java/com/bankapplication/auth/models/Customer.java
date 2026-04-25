package com.bankapplication.auth.models;

import jakarta.persistence.*;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_id", unique = true, nullable = false)
    private String customerId;

    @Column(name = "name", nullable = false)
    private String name;

    // BCrypt hashed password
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "enabled")
    private boolean enabled = true;

    // Default constructor (required by JPA)
    public Customer() {}

    // All args constructor
    public Customer(Long id, String customerId, String name,
                    String password, boolean enabled) {
        this.id = id;
        this.customerId = customerId;
        this.name = name;
        this.password = password;
        this.enabled = enabled;
    }

    // Getters & Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}