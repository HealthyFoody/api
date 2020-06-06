package com.healthyfoody.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.healthyfoody.dto.ApiResponse;
import com.healthyfoody.exception.ResourceNotFoundException;

public interface ResourceService<R extends ApiResponse, E,ID> extends EntityFetchService<E, ID>  {
	
    default Page<R> findAll(int page, int size) {
        throw new UnsupportedOperationException("Not implemented");
    }

    default List<R> findAll() {
        throw new UnsupportedOperationException("Not implemented");
    }

    default R findById(ID id) throws ResourceNotFoundException {
        throw new UnsupportedOperationException("Not implemented");
    }
}
