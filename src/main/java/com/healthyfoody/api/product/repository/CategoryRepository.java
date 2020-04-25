package com.healthyfoody.api.product.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthyfoody.api.product.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
	
}
