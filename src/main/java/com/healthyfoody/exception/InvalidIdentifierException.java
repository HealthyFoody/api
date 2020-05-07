package com.healthyfoody.exception;

public class InvalidIdentifierException extends RuntimeException {

    public InvalidIdentifierException(String message, Throwable cause) {
        super(message, cause);
    }
}
