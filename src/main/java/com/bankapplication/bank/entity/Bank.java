package com.bankapplication.bank.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "bank_code")
public class Bank {

    @Id
    @Column(name = "code")
    public int code;
    @NotBlank
    public String bankName;
}
