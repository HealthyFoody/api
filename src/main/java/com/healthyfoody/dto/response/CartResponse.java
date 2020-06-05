package com.healthyfoody.dto.response;

import java.util.List;

import com.healthyfoody.dto.ApiResponse;
import com.healthyfoody.dto.response.BaseProductResponse.ComboItemDto;
import com.healthyfoody.dto.response.BaseProductResponse.MealItemDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartResponse implements ApiResponse {
	
	String id;
	
	String customerId;
	
    List<MealItemDto> meals;

    List<ComboItemDto> combos;
}
