package com.healthyfoody.dto.response;

import com.healthyfoody.dto.ApiResponse;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CourierResponse implements ApiResponse {

	String id;
	
	String firstName;

	String lastName;

	Boolean active;
}
