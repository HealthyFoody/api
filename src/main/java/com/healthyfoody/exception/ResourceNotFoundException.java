package com.healthyfoody.exception;

public class ResourceNotFoundException extends BusinessException {

	public ResourceNotFoundException(Object id, Class<?> entityClass) {
		super(entityClass.getSimpleName() + ": No se encuentra el recurso con id " + id.toString());
	}

	public ResourceNotFoundException(Object value, String field, Class<?> entityClass) {
		super(entityClass.getSimpleName() + ": No se encuentra el recurso con " + field + " " + value.toString());
	}
}
