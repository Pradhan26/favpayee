package com.bankapplication.bank.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BankCodeResponseDto {
    public String code;
    public String bankName;
}
