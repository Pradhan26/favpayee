package com.bankapplication.bank.dto;


public class BankCodeResponseDto {
    public int code;
    public String bankName;

    public BankCodeResponseDto() {
    }
    public BankCodeResponseDto(String bankName, int code) {
        this.bankName = bankName;
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    @Override
    public String toString() {
        return "bankCodeResponseDto{" +
                "code=" + code +
                ", bankName='" + bankName + '\'' +
                '}';
    }
}
