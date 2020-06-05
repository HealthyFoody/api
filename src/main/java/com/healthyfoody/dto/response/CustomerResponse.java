package com.healthyfoody.dto.response;

import com.healthyfoody.dto.ApiResponse;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomerResponse implements ApiResponse {

	String id;
	
	String userId;
	
	String firstName;
	
	String lastName;
	
	String currentCart;
}
