package com.healthyfoody.service;

import com.healthyfoody.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CrudService<T, K> {

    default Page<T> findAll(int page, int size) {
        throw new UnsupportedOperationException("Not implemented");
    }

    default List<T> findAll() {
        throw new UnsupportedOperationException("Not implemented");
    }

    default T findById(K id) throws ResourceNotFoundException {
        throw new UnsupportedOperationException("Not implemented");
    }

    default T insert(T entity) {
        throw new UnsupportedOperationException("Not implemented");
    }

    default T update(T entity) {
        throw new UnsupportedOperationException("Not implemented");
    }

    default void delete(K id) {
        throw new UnsupportedOperationException("Not implemented");
    }
}
