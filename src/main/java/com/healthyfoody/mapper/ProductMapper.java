package com.healthyfoody.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.healthyfoody.dto.response.ComboResponse;
import com.healthyfoody.dto.response.ComboResponse.ComboGroup;
import com.healthyfoody.dto.response.MealResponse;
import com.healthyfoody.dto.response.ProductResponse;
import com.healthyfoody.entity.*;

@Mapper(config = SharedMapperConfig.class)
public interface ProductMapper extends ResponseMapper<ProductResponse, Product> {

	@Override
	default ProductResponse toResponse(Product entity) {
		if (entity != null) {
			if (entity instanceof Meal) {
				return mealToDto((Meal) entity);
			}
			if (entity instanceof Combo) {
				return comboToDto((Combo) entity);
			}
		}
		return null;
	}
	
	@Mapping(target = "toHour", source = "saleTimeSpan.endingHour")
	MealResponse mealToDto(Meal entity);

	@Mapping(target = "toHour", source = "saleTimeSpan.endingHour")
	@Mapping(target = "groups", source = "mealGroups")
	ComboResponse comboToDto(Combo entity);
	
	List<ComboGroup> mapComboDetail(List<MealGroup> list);
}
