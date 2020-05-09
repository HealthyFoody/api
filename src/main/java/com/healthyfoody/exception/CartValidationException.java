package com.healthyfoody.exception;

import com.healthyfoody.service.condition.ValidationError;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class CartValidationException extends BusinessException {

	Collection<ValidationError> errors;

	public CartValidationException(Collection<ValidationError> errors) {
		super("Error al validar los productos del carrito");
		this.errors = errors;
	}
}
