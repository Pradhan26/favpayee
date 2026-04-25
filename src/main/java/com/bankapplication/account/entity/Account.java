package com.bankapplication.account.entity;

import jakarta.persistence.*;

@Entity
@Table(
        name = "favorite_accounts",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"customer_id", "iban_number"})
        }
)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @Column(name = "customer_id", nullable = false, length = 50)
    private String customerId;

    @Column(name = "account_name", nullable = false, length = 100)
    private String accountName;

    @Column(name = "iban_number", nullable = false, length = 34)
    private String ibanNumber;

    @Column(name = "bank_code", nullable = false)
    private int bankCode;

    @Column(name = "bank_name", length = 100)
    private String bankName;

    public Account() {}

    public Account(Long accountId, String customerId, String accountName,
                   String ibanNumber, int bankCode, String bankName) {
        this.accountId = accountId;
        this.customerId = customerId;
        this.accountName = accountName;
        this.ibanNumber = ibanNumber;
        this.bankCode = bankCode;
        this.bankName = bankName;
    }

    public Long getAccountId() { return accountId; }
    public void setAccountId(Long accountId) { this.accountId = accountId; }

    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }

    public String getAccountName() { return accountName; }
    public void setAccountName(String accountName) { this.accountName = accountName; }

    public String getIbanNumber() { return ibanNumber; }
    public void setIbanNumber(String ibanNumber) { this.ibanNumber = ibanNumber; }

    public int getBankCode() { return bankCode; }
    public void setBankCode(int bankCode) { this.bankCode = bankCode; }

    public String getBankName() { return bankName; }
    public void setBankName(String bankName) { this.bankName = bankName; }
}