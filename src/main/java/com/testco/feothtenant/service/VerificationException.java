package com.testco.feothtenant.service;

public class VerificationException extends RuntimeException{
    public VerificationException() {
        super();
    }

    public VerificationException(String message) {
        super(message);
    }

    public VerificationException(String message, Throwable cause) {
        super(message, cause);
    }
}
