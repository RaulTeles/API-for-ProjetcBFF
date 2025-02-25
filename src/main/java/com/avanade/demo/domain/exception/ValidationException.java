package com.avanade.demo.domain.exception;

public class ValidationException extends RuntimeException {
    public ValidationException(String nessage) {
        super(nessage);
    }
}
