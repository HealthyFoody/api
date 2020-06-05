package com.healthyfoody.dto.response;

import com.healthyfoody.dto.ApiResponse;

import lombok.Getter;

@Getter
public class AuthResponse implements ApiResponse {

	private final String token;

	public AuthResponse(String token) {
		this.token = token;
	}
}