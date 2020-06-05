package com.healthyfoody.exception;

public class EmailExistsException extends BusinessException {

    public EmailExistsException(String email) {
        super("Ya existe una cuenta registrada con el email " + email);
    }
}
