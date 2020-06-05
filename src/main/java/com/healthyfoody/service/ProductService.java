package com.healthyfoody.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;

import com.healthyfoody.dto.response.ProductResponse;
import com.healthyfoody.entity.*;
import com.healthyfoody.exception.ResourceNotFoundException;

public interface ProductService extends ResourceService<ProductResponse, Product, UUID> {

    Page<ProductResponse> findAllByCategory(UUID categoryId, int page, int size) throws ResourceNotFoundException;

    ProductResponse findTypedById(UUID id, ProductType type) throws ResourceNotFoundException;

    //FIXME: change type
    List<MealGroup> loadComboDetail(UUID comboId, Boolean verifyExpired) throws ResourceNotFoundException;

	Combo findComboEntityById(UUID id) throws ResourceNotFoundException;

	Meal findMealEntityById(UUID id) throws ResourceNotFoundException;
}
