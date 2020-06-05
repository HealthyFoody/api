package com.healthyfoody.dto.request;

import java.util.UUID;

import com.healthyfoody.dto.ApiRequest;
import com.healthyfoody.validation.annotations.GUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressRequest implements ApiRequest {
	
	@GUID
	UUID customerId;
	
	String name;
	
	Double latitude;
	
	Double longitude;
	
	String fullAddress;
}
