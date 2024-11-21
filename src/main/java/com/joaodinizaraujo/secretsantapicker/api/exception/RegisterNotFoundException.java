package com.joaodinizaraujo.secretsantapicker.api.exception;

public class RegisterNotFoundException extends IllegalArgumentException {
    public RegisterNotFoundException(String message) {
        super(message);
    }
}
