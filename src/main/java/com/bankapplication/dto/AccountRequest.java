package com.bankapplication.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class AccountRequest {

    @NotBlank(message = "CustomerId is required")
    private String customerId;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "Account name is required")
    @Pattern(regexp = "^[a-zA-Z0-9'-]+$", message = "Invalid account name")
    private String accountName;

    @NotBlank(message = "IBAN is required")
    @Size(max = 20, message = "IBAN max length is 20")
    private String iban;

    public AccountRequest() {
    }

    public AccountRequest(String customerId, String password, String accountName, String iban) {
        this.customerId = customerId;
        this.password = password;
        this.accountName = accountName;
        this.iban = iban;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }
}