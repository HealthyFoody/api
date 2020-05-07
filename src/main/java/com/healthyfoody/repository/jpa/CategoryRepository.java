package com.healthyfoody.repository.jpa;

import com.healthyfoody.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
	
}
