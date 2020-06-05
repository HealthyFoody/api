package com.healthyfoody.dto.request;

import com.healthyfoody.dto.ApiRequest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest implements ApiRequest {
	
	String email;
	
	String password;
}
