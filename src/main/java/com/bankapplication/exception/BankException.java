package com.bankapplication.exception;

public class BankException extends RuntimeException {

    public BankException() {
    }

    public BankException(String message) {
        super(message);
    }
}