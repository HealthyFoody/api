package com.healthyfoody.mapper;

import com.healthyfoody.dto.ComboModelDto;
import com.healthyfoody.dto.MealModelDto;
import com.healthyfoody.dto.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.healthyfoody.entity.Combo;
import com.healthyfoody.entity.Meal;
import com.healthyfoody.entity.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {

	default ProductResponse productToDto(Product entity) {
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

	@Mapping(target = "saleStartsAt", source = "saleTimeSpan.startingHour")
	@Mapping(target = "saleStopsAt", source = "saleTimeSpan.endingHour")
	MealModelDto mealToDto(Meal entity);

	@Mapping(target = "saleStartsAt", source = "saleTimeSpan.startingHour")
	@Mapping(target = "saleStopsAt", source = "saleTimeSpan.endingHour")
	ComboModelDto comboToDto(Combo entity);
}
