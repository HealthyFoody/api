package com.healthyfoody.dto.response;

import java.time.LocalDateTime;

import com.healthyfoody.dto.ApiResponse;
import com.healthyfoody.entity.AccountStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse implements ApiResponse {

	String email;

	String role;
	
	String firstName;

	String lastName;

	Boolean emailValidated;

	AccountStatus statusCode;

	LocalDateTime registeredOn;

	LocalDateTime lastAccessOn;
}
