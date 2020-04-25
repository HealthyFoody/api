package com.healthyfoody.api.product.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthyfoody.api.product.entity.Category;
import com.healthyfoody.api.product.repository.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	CategoryRepository categoryRepository;
	
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}
	
	public Optional<Category> findById(UUID id) {

		return categoryRepository.findById(id);
	}
}
