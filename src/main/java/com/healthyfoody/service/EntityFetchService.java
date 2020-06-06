package com.healthyfoody.service;

import com.healthyfoody.exception.ResourceNotFoundException;

public interface EntityFetchService<E, ID> {
	
    E findEntityById(ID id) throws ResourceNotFoundException;
}
