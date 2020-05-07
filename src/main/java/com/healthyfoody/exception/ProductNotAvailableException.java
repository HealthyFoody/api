package com.healthyfoody.exception;

import java.util.List;
import java.util.UUID;

public class ProductNotAvailableException extends RuntimeException {

    UUID id;

    List<String> errors;

    public ProductNotAvailableException(UUID id) {
        super("Producto no disponible");


    }
}
