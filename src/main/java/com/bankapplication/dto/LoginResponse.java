package com.bankapplication.dto;

public class LoginResponse {

    private String message;
    private String customerId;
    private String name;

    public LoginResponse() {
    }

    public LoginResponse(String message, String customerId, String name) {
        this.message = message;
        this.customerId = customerId;
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
}