package com.healthyfoody.mapper;

import java.util.Collection;
import java.util.List;

import com.healthyfoody.dto.ApiRequest;

public interface RequestMapper<R extends ApiRequest, E> {
	
	E toEntity(R request);
	
	List<E> mapEntityList(Collection<R> requests);
	
	void updateEntity(R request, E entity);
}
