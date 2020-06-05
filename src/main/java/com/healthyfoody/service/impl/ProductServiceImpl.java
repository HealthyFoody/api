package com.healthyfoody.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.healthyfoody.dto.response.ProductResponse;
import com.healthyfoody.entity.*;
import com.healthyfoody.exception.ResourceNotFoundException;
import com.healthyfoody.mapper.ProductMapper;
import com.healthyfoody.repository.jpa.ProductRepository;
import com.healthyfoody.service.ProductService;
import com.healthyfoody.util.PageMapper;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	ProductMapper productMapper;
	
	@Override
	public Page<ProductResponse> findAllByCategory(UUID categoryId, int page, int size) {

		Page<Product> result = productRepository.findAllFromCategory(categoryId, PageRequest.of(page, size));
		return PageMapper.mapPage(result, productMapper);
	}

	@Override
	public ProductResponse findTypedById(UUID id, ProductType type) throws ResourceNotFoundException {

		Product result = null;
		switch (type) {
		case MEAL:
			result = findMealEntityById(id);
			break;
		case COMBO:
			result = findComboEntityById(id);
			break;
		case ANY:
		default:
			result = findEntityById(id);
			break;
		}		

		return productMapper.toResponse(result);
	}

	@Override
	public List<MealGroup> loadComboDetail(UUID comboId, Boolean verifyExpired) throws ResourceNotFoundException {
		Combo combo = (Combo) productRepository.findById(comboId).filter(x -> x instanceof Combo)
				.orElseThrow(() -> new ResourceNotFoundException(comboId, Combo.class));

		if (verifyExpired && combo.getToDate().compareTo(LocalDate.now()) < 0) {
			throw new RuntimeException("El combo seleccionado no està a la venta");
		}
		return combo.getMealGroups();
	}


	@Override
	public ProductResponse findById(UUID id) throws ResourceNotFoundException {
		return findTypedById(id, ProductType.ANY);
	}

	@Override
	public Product findEntityById(UUID id) throws ResourceNotFoundException {
		return findTypedEntityById(id, Product.class);
	}
	
	@Override
	public Combo findComboEntityById(UUID id) throws ResourceNotFoundException {
		return (Combo) findTypedEntityById(id, Combo.class);
	}
	
	@Override
	public Meal findMealEntityById(UUID id) throws ResourceNotFoundException {
		return (Meal) findTypedEntityById(id, Meal.class);
	}
	
	private Product findTypedEntityById(UUID id, Class<?> entityClass) {
		return productRepository.findById(id)
				.filter(entityClass::isInstance)
				.orElseThrow(() -> new ResourceNotFoundException(id, entityClass));
	}
}
