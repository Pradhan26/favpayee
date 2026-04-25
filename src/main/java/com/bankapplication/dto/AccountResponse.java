package com.bankapplication.dto;

public class AccountResponse {

    private Long id;
    private String accountName;
    private String iban;
    private String bankName;
    private String customerName;

    public AccountResponse() {
    }

    public AccountResponse(Long id, String accountName, String iban,
                           String bankName, String customerName) {
        this.id = id;
        this.accountName = accountName;
        this.iban = iban;
        this.bankName = bankName;
        this.customerName = customerName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
