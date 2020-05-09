package com.healthyfoody.mapper;

import com.healthyfoody.dto.CategoryDto;
import com.healthyfoody.entity.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    List<CategoryDto> categoriesToDtoList(List<Category> entities);

    CategoryDto categoryToDto(Category entity);
}
