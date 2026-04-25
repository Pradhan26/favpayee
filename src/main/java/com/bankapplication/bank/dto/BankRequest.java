package com.bankapplication.bank.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BankRequest {

    @NotBlank(message = "Bank code is required")
    public String code;

    @NotBlank(message = "BankName is required")
    public String bankName;
}
