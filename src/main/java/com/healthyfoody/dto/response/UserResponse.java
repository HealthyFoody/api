package com.healthyfoody.dto.response;

import java.time.LocalDateTime;

import com.healthyfoody.dto.ApiResponse;
import com.healthyfoody.entity.AccountStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse implements ApiResponse {

	protected String email;

	protected String role;

	protected String firstName;

	protected String lastName;

	protected Boolean emailValidated;

	protected AccountStatus statusCode;

	protected LocalDateTime registeredOn;

	protected LocalDateTime lastAccessOn;
}
