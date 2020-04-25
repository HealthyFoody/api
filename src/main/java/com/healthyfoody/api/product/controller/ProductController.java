package com.healthyfoody.api.product.controller;

import java.util.List;
import java.util.UUID;

import com.healthyfoody.api.common.ValidUUID;
import com.healthyfoody.api.product.controller.form.StockRequestForm;
import com.healthyfoody.api.product.controller.resource.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.healthyfoody.api.product.entity.Category;
import com.healthyfoody.api.product.entity.Group;
import com.healthyfoody.api.product.entity.Product;
import com.healthyfoody.api.product.controller.resource.CategoryModel;
import com.healthyfoody.api.product.controller.resource.assembler.CategoryAssembler;
import com.healthyfoody.api.product.controller.resource.assembler.ProductAssembler;
import com.healthyfoody.api.product.service.CategoryService;
import com.healthyfoody.api.product.service.ProductService;

import javax.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	CategoryService categoryService;
	@Autowired
	ProductService productService;
	@Autowired
	CategoryAssembler categoryAssembler;
	@Autowired
	ProductAssembler productAssembler;
	@Autowired
	PagedResourcesAssembler<Product> pagedProductsAssembler;
	
	// Obtiene todas las categorias (sin paginar)
	@GetMapping("/categories")
	public ResponseEntity<CollectionModel<CategoryModel>> getAllCategories() {
		List<Category> list = categoryService.findAll();
		
		return new ResponseEntity<>(categoryAssembler.toCollectionModel(list), HttpStatus.OK);
	}
	
	@GetMapping("/products")
	public ResponseEntity<?> getProductsByCategory(
			@RequestParam(name = "category") UUID categoryId,
			@RequestParam(defaultValue = "0") Integer page) {
		Page<Product> products = productService.findByCategoryId(categoryId, page);

		PagedModel<ProductModel> pagedModel = pagedProductsAssembler
				.toModel(products, productAssembler);

		return ResponseEntity.ok(pagedModel);
	}
	
	@GetMapping("/combo/{id}")
	public ResponseEntity<?> getComboDetail(@PathVariable UUID id){
		
		
		List<Group> comboDetail = productService.getComboDetail(id);
		
		return ResponseEntity.ok(comboDetail);
	}
	
	
	@GetMapping("/verify")
	public ResponseEntity<?> getAvailability(@RequestBody StockRequestForm form) {
		
		// TODO: comprobar que exiten parametros de busqueda
		if (form.getStoreId() != null && form.getHour() != null) {
			return ResponseEntity.badRequest().build();
		}
		
		// TODO: identificar ProductIDs validos

		
		// TODO: hallar resultados para IDs validos con parametros presentes
		
		return null;
	}
	
	
	/* CRUD */
	@GetMapping("/category/{id}")
	public ResponseEntity<?> getCategoryById(@PathVariable UUID id) {
		
		 return categoryService.findById(id)
				 .map(categoryAssembler::toModel)
				 .map(ResponseEntity::ok)
				 .orElse(ResponseEntity.badRequest().build());
	}

	@GetMapping("/products/{id}")
	public ResponseEntity<?> getProductById(@PathVariable UUID id) {

		return productService.findById(id)
				.map(productAssembler::toModel)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
}
