package com.healthyfoody.mapper;

import org.mapstruct.Mapper;

import com.healthyfoody.dto.response.CategoryResponse;
import com.healthyfoody.entity.Category;

@Mapper(config = SharedMapperConfig.class)
public interface CategoryMapper extends ResponseMapper<CategoryResponse, Category> {
	
	CategoryResponse toResponse(Category entity);
}
