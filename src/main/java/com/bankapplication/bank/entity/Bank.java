package com.bankapplication.bank.entity;

import com.bankapplication.account.entity.Account;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "bank_code")
public class Bank {

    @Id
    @Column(name = "code")
    private int code;

    @Column(name = "bank_name", nullable = false)
    private String bankName;

    @OneToMany(mappedBy = "bank", fetch = FetchType.LAZY)
    private List<Account> accounts;

    public Bank() {}

    public Bank(int code, String bankName) {
        this.code = code;
        this.bankName = bankName;
    }

    public int getCode() { return code; }
    public void setCode(int code) { this.code = code; }

    public String getBankName() { return bankName; }
    public void setBankName(String bankName) { this.bankName = bankName; }

    public List<Account> getAccounts() { return accounts; }
    public void setAccounts(List<Account> accounts) { this.accounts = accounts; }
}