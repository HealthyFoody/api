package com.healthyfoody.mapper;

import java.util.UUID;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.healthyfoody.dto.response.BaseProductResponse.ComboItemDto;
import com.healthyfoody.dto.response.BaseProductResponse.ComponentDto;
import com.healthyfoody.dto.response.BaseProductResponse.MealItemDto;
import com.healthyfoody.dto.response.CartResponse;
import com.healthyfoody.entity.redis.Cart;
import com.healthyfoody.entity.redis.CartCombo;
import com.healthyfoody.entity.redis.CartMeal;

@Mapper(config = SharedMapperConfig.class)
public interface CartMapper extends ResponseMapper<CartResponse, Cart> {

	@Mapping(target = "customerId", ignore = true)
	CartResponse toResponse(Cart entity);
	
	@Mapping(target = "name", ignore = true)
	@Mapping(target = "price", ignore = true)
	@Mapping(target = "components", source = "bundleComponents")
	@Mapping(target = "id", source = "comboId")
	ComboItemDto cartComboToComboItemDto(CartCombo cartCombo);
	
	@Mapping(target = "id", source = "productId")
	@Mapping(target = "name", ignore = true)
	@Mapping(target = "price", ignore = true)
	MealItemDto cartMealToMealItemDto(CartMeal cartMeal);
	
	default ComponentDto mapComponent(UUID id) {
		ComponentDto component = new ComponentDto();
		component.setId(id.toString());
		return component;
	}
	
}
