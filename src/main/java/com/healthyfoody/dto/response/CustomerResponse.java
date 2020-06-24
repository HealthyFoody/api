package com.healthyfoody.dto.response;

import com.healthyfoody.dto.ApiResponse;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomerResponse extends UserResponse {

	String id;

	String currentCart;
}
