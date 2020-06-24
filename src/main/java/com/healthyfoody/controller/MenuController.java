package com.healthyfoody.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.healthyfoody.entity.MealGroup;
import com.healthyfoody.mapper.CategoryMapper;
import com.healthyfoody.mapper.ProductMapper;
import com.healthyfoody.service.CategoryService;
import com.healthyfoody.service.ProductService;
import com.healthyfoody.validation.annotations.GUID;

@Validated
@RestController
@RequestMapping("/menu")
public class MenuController {

	@Autowired
	CategoryService categoryService;

	@Autowired
	ProductService productService;
	
	@Autowired
	CategoryMapper categoryMapper;
	
	@Autowired
	ProductMapper productMapper;

	@GetMapping("/categories")
	public ResponseEntity<?> getAllCategories() {

		return ResponseEntity.ok(categoryService.findAll());
	}

	@GetMapping("/products")
	public ResponseEntity<?> getProductsByCategory(
			@RequestParam(name = "category") @GUID String categoryId,
			@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {

		return ResponseEntity.ok(productService.findAllByCategory(UUID.fromString(categoryId), page, size).getContent());
	}

	@GetMapping("products/{id}/combo")
	public ResponseEntity<?> getComboDetail(
			@PathVariable @GUID String id,
			@RequestParam(required = false, defaultValue = "true") Boolean validDate) {

		List<MealGroup> mg = productService.loadComboDetail(UUID.fromString(id),validDate);
		return ResponseEntity.ok(productMapper.mapComboDetail(mg));
	}

	@GetMapping("/categories/{id}")
	public ResponseEntity<?> getCategoryById(@PathVariable @GUID String id) {

		return ResponseEntity.ok(categoryService.findById(UUID.fromString(id)));
	}

	@GetMapping("/products/{id}")
	public ResponseEntity<?> getProductById(@PathVariable @GUID String id) {
		return ResponseEntity.ok(productService.findById(UUID.fromString(id)));
	}

}
