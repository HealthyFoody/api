package com.healthyfoody.exception;

import java.util.Collection;

public class CartValidationException extends BusinessException {

	Collection<ValidationError> errors;
	
	public CartValidationException() {
		super("Error al validar el carrito");
	}

	public CartValidationException(String message) {
		super(message);
	}
	
	public CartValidationException(Collection<ValidationError> errors) {
		super("Error al validar los productos del carrito");
		this.errors = errors;
	}
}
 