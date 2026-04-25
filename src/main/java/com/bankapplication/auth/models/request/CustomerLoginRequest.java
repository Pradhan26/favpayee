package com.bankapplication.auth.models.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerLoginRequest {

    private String customerId;
    private String password;
}