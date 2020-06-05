package com.healthyfoody.service;

import java.util.UUID;

import com.healthyfoody.dto.response.CategoryResponse;
import com.healthyfoody.entity.Category;

public interface CategoryService extends ResourceService<CategoryResponse, Category, UUID> {
}
