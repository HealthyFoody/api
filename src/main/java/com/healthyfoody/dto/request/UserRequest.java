package com.healthyfoody.dto.request;

import com.healthyfoody.dto.ApiRequest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest implements ApiRequest {
	
	String email;
	
	String password;
	
	String firstName;
	
	String lastName;
}
