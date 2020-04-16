package com.healthyfoody.api;

import java.util.Collection;
public interface DaoService<E, ID> {
	E insert(E entity);
	E update(E entity);
	E findById(ID id);
	Collection<E> findAll();
	ID delete(ID id);
	
}
