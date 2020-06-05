package com.healthyfoody.dto.response;

import com.healthyfoody.dto.ApiResponse;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddressResponse implements ApiResponse {
	
	String id;
	
	String customerId;
	
	String name;

	Double latitude;

	Double longitude;

	String fullAddress;

	Boolean isDefault;
}
