package com.demo.authservice.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(
        name = "customer_id",
        unique = true,
        nullable = false
    )
    private String customerId;

    @Column(
        name = "name",
        nullable = false
    )
    private String name;

    /*
      BCrypt hashed password
     */
    @Column(
        name = "password",
        nullable = false
    )
    private String password;

    @Builder.Default
    private boolean enabled = true;
}

