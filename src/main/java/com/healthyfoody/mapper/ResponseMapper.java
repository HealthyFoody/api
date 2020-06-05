package com.healthyfoody.mapper;

import java.util.Collection;
import java.util.List;

import com.healthyfoody.dto.ApiResponse;

public interface ResponseMapper<R extends ApiResponse, E> {
	
	R toResponse(E entity);
	
	List<R> mapResponseList(Collection<E> entities); 
}
