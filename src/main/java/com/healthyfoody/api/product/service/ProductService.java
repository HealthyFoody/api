package com.healthyfoody.api.product.service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.healthyfoody.api.product.ProductType;
import com.healthyfoody.api.product.entity.Combo;
import com.healthyfoody.api.product.entity.Meal;
import com.healthyfoody.api.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.healthyfoody.api.product.entity.Group;
import com.healthyfoody.api.product.entity.Product;

@Service
public class ProductService {
	
	@Autowired
	ProductRepository productRepository;

	public Page<Product> findByCategoryId(UUID id, Integer page){

		Pageable pageable = PageRequest.of(page, 15);

		return productRepository.findAllFromCategory(id, pageable);
	}

	public Optional<Product> findById(UUID id) {

		return productRepository.findById(id);
	}

	public Optional<Product> findTypedById(UUID id, ProductType type) {

		Optional<Product> product = productRepository.findById(id);

		switch (type) {
			case MEAL:
				return product.get() instanceof Meal ? product : Optional.empty();
			case COMBO:
				return product.get() instanceof Combo ? product : Optional.empty();
			default:
				return product;
		}
	}
	
	public List<Group> getComboDetail(UUID id) {

		Product product = productRepository.findById(id).orElse(null);

		System.out.println(id);
		System.out.println(product);

		if (product != null)
			if (product instanceof Combo) {

				Combo combo = (Combo) product;
				return combo.getGroups();
			}
		return null;
	}

	public Boolean isInStockAtStore(UUID productId, UUID storeId) {
		return productRepository.isThereStockAtStore(productId, storeId);
	}

	public Boolean isOnSaleAtHour(UUID productId, LocalTime hour) {
		return productRepository.isOnSaleSpan(productId, hour);

	}

	public Boolean isPartOfCombo(UUID productId, UUID comboId) {
		Product child = productRepository.findById(productId)
				.orElseThrow(() -> new RuntimeException("Invalid product id: " + productId.toString()));

		Product parent = productRepository.findById(comboId)
				.orElseThrow(() -> new RuntimeException("Invalid combo id: " + comboId.toString()));

		Combo combo = (Combo) parent;

		if (combo.getGroups() == null)
			return false;

		for (Group group: combo.getGroups()) {
			for (Meal meal: group.getMeals()) {
				if (meal.getId() == productId)
					return true;
			}
		}
		return false;
	}
}
