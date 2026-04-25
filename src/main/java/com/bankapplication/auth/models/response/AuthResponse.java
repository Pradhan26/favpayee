package com.bankapplication.auth.models.response;


public class AuthResponse {

    private String customerId;
    private String token;

    public AuthResponse() {}

    public AuthResponse(String customerId, String token) {
        this.customerId = customerId;
        this.token = token;
    }

    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
}