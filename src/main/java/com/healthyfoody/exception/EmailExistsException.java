package com.healthyfoody.exception;

public class EmailExistsException extends BusinessException {

    public EmailExistsException() {
        super("El email ingresado ya existe");
    }
}
