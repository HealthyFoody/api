package com.healthyfoody.dto.response;

import com.healthyfoody.dto.ApiResponse;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CategoryResponse implements ApiResponse {

	String id;
	
    String name;

    String description;
    
    String imageUrl;
}
