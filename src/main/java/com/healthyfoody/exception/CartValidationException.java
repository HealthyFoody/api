package com.healthyfoody.exception;

import java.util.Map;

public class CartValidationException extends BusinessException {

	private Map<String, ?> errors;

	public CartValidationException(Map<String, ?> errors) {
		super("Error al validar los productos del carrito");
		this.errors = errors;
	}
}
