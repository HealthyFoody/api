package com.healthyfoody.exception;

import java.util.UUID;

public class ResourceNotFoundException extends RuntimeException {

	public ResourceNotFoundException(UUID id, Class<?> entityClass) {
		super(entityClass.getSimpleName() + ": No se encuentra el recurso con id " + id.toString());
	}
}
