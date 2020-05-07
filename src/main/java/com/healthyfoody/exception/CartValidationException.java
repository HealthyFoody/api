package com.healthyfoody.exception;

import java.util.Map;

public class CartValidationException extends RuntimeException {

	private Map<String, ?> report;

	public CartValidationException(Map<String, ?> report) {
		super("Error al validar los productos del carrito");
		this.report = report;
	}
}
