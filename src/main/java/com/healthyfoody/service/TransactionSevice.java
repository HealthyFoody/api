package com.healthyfoody.service;

import com.healthyfoody.dto.ApiRequest;
import com.healthyfoody.dto.ApiResponse;

public interface TransactionSevice<O extends ApiResponse, I extends ApiRequest, ID> {
	
	O insert(I request);
	
	O update(I request, ID id);
	
	void delete(ID id);
}
