package com.healthyfoody.exception;

public class ValidationError {
    String message;
    Object source;

    public ValidationError(String message, Object source) {
        this.message = message;
        this.source = source;
    }
    public ValidationError(String message) {
        this.message = message;
        this.source = null;
    }
}
