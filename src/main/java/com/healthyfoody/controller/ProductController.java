package com.healthyfoody.controller;

import com.healthyfoody.service.CategoryService;
import com.healthyfoody.service.ProductService;
import com.healthyfoody.validation.ValidUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Validated
@RestController
@RequestMapping("/menu")
public class ProductController {

	@Autowired
	CategoryService categoryService;

	@Autowired
	ProductService productService;

	@GetMapping("/categories")
	public ResponseEntity<?> getAllCategories() {

		return ResponseEntity.ok(categoryService.findAll());
	}

	@GetMapping("/products")
	public ResponseEntity<?> getProductsByCategory(
			@RequestParam(name = "category") @ValidUUID String categoryId,
			@RequestParam(required = false, defaultValue = "0") Integer page,
			@RequestParam(required = false, defaultValue = "10") Integer size) {

		return ResponseEntity.ok(productService.findAllByCategory(UUID.fromString(categoryId), page, size));
	}

	@GetMapping("products/{id}/combo")
	public ResponseEntity<?> getComboDetail(
			@PathVariable @ValidUUID String id,
			@RequestParam(required = false, defaultValue = "true") Boolean validDate) {

		return ResponseEntity.ok(productService.loadComboDetail(UUID.fromString(id),validDate));
	}

	@GetMapping("/categories/{id}")
	public ResponseEntity<?> getCategoryById(@PathVariable @ValidUUID String id) {

		return ResponseEntity.ok(categoryService.findById(UUID.fromString(id)));
	}

	@GetMapping("/products/{id}")
	public ResponseEntity<?> getProductById(@PathVariable @ValidUUID String id) {

		return ResponseEntity.ok(productService.findById(UUID.fromString(id)));
	}

}
