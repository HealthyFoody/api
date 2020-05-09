package com.healthyfoody.service;

import com.healthyfoody.entity.*;
import com.healthyfoody.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public interface ProductService extends CrudService<Product, UUID> {

    Page<Product> findAllByCategory(UUID categoryId, int page, int size) throws ResourceNotFoundException;

    Product findTypedById(UUID id, ProductType type) throws ResourceNotFoundException;

    List<MealGroup> loadComboDetail(UUID comboId, Boolean verifyExpired) throws ResourceNotFoundException;
}
