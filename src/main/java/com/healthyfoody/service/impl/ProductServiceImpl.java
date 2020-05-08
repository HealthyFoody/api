package com.healthyfoody.service.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.healthyfoody.entity.Combo;
import com.healthyfoody.entity.Meal;
import com.healthyfoody.entity.MealGroup;
import com.healthyfoody.entity.Product;
import com.healthyfoody.entity.ProductType;
import com.healthyfoody.exception.ResourceNotFoundException;
import com.healthyfoody.repository.jpa.ProductRepository;
import com.healthyfoody.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;

	@Override
	public Page<Product> findAllByCategory(UUID categoryId, int page, int size) {

		Pageable pageable = PageRequest.of(page, size);

		return productRepository.findAllFromCategory(categoryId, pageable);
	}

	@Override
	public Product findTypedById(UUID id, ProductType type) throws ResourceNotFoundException {

		Optional<Product> result = productRepository.findById(id);
		Class<?> auxClass = null;

		switch (type) {
		case MEAL:
			auxClass = Meal.class;
			break;
		case COMBO:
			auxClass = Combo.class;
			break;
		case ANY:
		default:
			auxClass = Product.class;
			break;
		}

		Class<?> entityClass = auxClass;
		return result.filter(entityClass::isInstance).orElseThrow(() -> new ResourceNotFoundException(id, entityClass));
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
	public List<String> validationReport(UUID productId, boolean checkListed, UUID storeId, LocalTime hour) {

		Product product = null;
		List<String> errors = new ArrayList<>();

		try {
			product = findById(productId);
		} catch (RuntimeException e) {
			errors.add(e.getMessage());
			return errors;
		}
		if (product == null)return errors;

		if (checkListed && !product.getListed()) {
			errors.add("El producto seleccionado no se encuentra a la venta");
			return errors;
		}

		if (storeId != null && !isInStock(storeId, product.getId())) {
			errors.add("El producto seleccionado se ha agotado en esa tienda");
		}

		if (hour != null && !isOnSaleAtHour(product.getId(), hour)) {
			errors.add("Está fuera del horario de venta del producto seleccionado");
		}
		return errors;
	}

	@Override
	public Boolean isInStock(UUID storeId, UUID productId) {
		return productRepository.isThereStockAtStore(productId, storeId);
	}

	@Override
	public Boolean isOnSaleAtHour(UUID productId, LocalTime hour) {
		return productRepository.isOnSaleSpan(productId, hour);
	}

	@Override
	public Boolean validateCombination(UUID comboId, List<UUID> productsIds) {

		Combo combo = (Combo) productRepository.findById(comboId).orElse(null);

		if (combo == null)
			return false;

		for (MealGroup mealGroup : combo.getMealGroups()) {
			long matches = mealGroup.getMeals().stream().filter(meal -> productsIds.contains(meal.getId())).count();

			boolean isOptional = mealGroup.getOptional();

			if (isOptional && matches > 1)
				return false;
			if (!isOptional && matches != 1)
				return false;
		}
		return true;
	}

	@Override
	public Product findById(UUID id) throws ResourceNotFoundException {
		return findTypedById(id, ProductType.ANY);
	}
}
