package com.healthyfoody.controller;

import java.util.*;

import com.healthyfoody.dto.response.ProductResponse;
import com.healthyfoody.entity.BaseEntity;
import com.healthyfoody.entity.Product;
import com.healthyfoody.exception.ResourceNotFoundException;
import com.healthyfoody.util.GUIDWrapper;
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

import javax.validation.Valid;

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

	@GetMapping("/details/products")
	public ResponseEntity<?> getProductsDetailed(@RequestParam UUID[] products) {
		Set<Object> prodList = new HashSet<>();
		Set<UUID> notFound = new HashSet<>();
		Map<String, Object> response = new HashMap<>();
		for (UUID id : products) {
			try {
				ProductResponse r = productService.findById(id);
				prodList.add(r);
			} catch (ResourceNotFoundException e) {
				notFound.add(id);
			}
		}
		response.put("products", prodList);
		response.put("not_found", notFound);
		return ResponseEntity.ok(response);
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
