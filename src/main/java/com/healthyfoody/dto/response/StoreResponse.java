package com.healthyfoody.dto.response;

import com.healthyfoody.dto.ApiResponse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreResponse implements ApiResponse {
	
	String id;
	
	String description;

	Double latitude;

	Double longitude;
}
