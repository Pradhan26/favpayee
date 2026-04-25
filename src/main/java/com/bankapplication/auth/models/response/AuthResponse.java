package com.bankapplication.auth.models.response;


public class AuthResponse {

    private String customerId;
    private String token;
    private String name;

    public AuthResponse() {}

    public AuthResponse(String customerId, String token, String name) {
        this.customerId = customerId;
        this.token = token;
        this.name = name;
    }

    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}