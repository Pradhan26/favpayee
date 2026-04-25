package com.bankapplication.bank.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "bank_code")
public class Bank {

    @Id
    @Column(name = "code")
    public int code;
    public String bankName;
}
