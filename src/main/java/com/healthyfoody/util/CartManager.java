package com.healthyfoody.util;

import java.time.LocalTime;
import java.util.*;

import com.healthyfoody.entity.*;
import com.healthyfoody.entity.redis.Cart;
import com.healthyfoody.entity.redis.CartCombo;
import com.healthyfoody.entity.redis.CartMeal;
import com.healthyfoody.exception.BusinessException;
import com.healthyfoody.exception.CartValidationException;
import com.healthyfoody.service.ProductService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public class CartManager {

	@Getter
	private final Cart cart;
	private final ProductService productService;
	private final PersistenceListener persistenceListener;
	@Setter
	private Store store;
	@Setter
	private LocalTime programmedHour;

	public void addItem(Product product, int quantityOrInstance, List<UUID> components, boolean override) {
		
		// FIXME: replace assert statement
		if (quantityOrInstance <= 0) {
			throw new BusinessException("Error al agregar producto a la orden");
		}

		if (product instanceof Meal) {

			CartMeal meal = new CartMeal(product.getId(), quantityOrInstance);
			Set<CartMeal> meals = cart.getMeals();

			if (override)
				meals.remove(meal);
			meals.add(meal);
		}
		if (product instanceof Combo) {

			CartCombo combo = new CartCombo(product.getId(), quantityOrInstance, components);
			Set<CartCombo> combos = cart.getCombos();

			if (override)
				combos.remove(combo);
			combos.add(combo);
		}
	}

	public void deleteItem(Product product, Integer instance) {
		
		if (product instanceof Meal) {
			CartMeal meal = new CartMeal(product.getId(), null);
			cart.getMeals().remove(meal);

		}
		if (product instanceof Combo) {
			CartCombo combo = new CartCombo(product.getId(), instance, null);
			cart.getCombos().remove(combo);
		}
	}

	public void clear() {
		cart.setCombos(new HashSet<>());
		cart.setMeals(new HashSet<>());
	}

	public List<OrderProduct> processCart() {
		if (store == null || programmedHour == null)
			throw new CartValidationException();
		
		this.lock();

		List<OrderProduct> orderProducts = new ArrayList<>();

		try {
			processMeals(orderProducts);
			proccessCombos(orderProducts);
			
		} catch (Exception e) {
			this.unlock();
		}

		return orderProducts;
	}
	
	private void processMeals(List<OrderProduct> orderProducts) {
		for (CartMeal item : cart.getMeals()) {
			OrderProduct op = new OrderProduct();
			Product p = productService.findEntityById(item.getProductId());

			op.setProduct(p);
			op.setPrice(p.getPrice());
			op.setQuantity(item.getQuantity());
			op.setInstance(1);
			op.setIsCombo(false);

			orderProducts.add(op);
		}
	}
	
	private void proccessCombos(List<OrderProduct> orderProducts) {
		for (CartCombo item : cart.getCombos()) {
			OrderProduct op = new OrderProduct();
			Combo c = productService.findComboEntityById(item.getComboId());
			op.setProduct(c);
			op.setPrice(c.getPrice());
			op.setQuantity(1);
			op.setInstance(item.getInstance());
			op.setIsCombo(true);

			Set<OrderProductComponent> components = new HashSet<>();

			List<Meal> mealsInCombo = new ArrayList<>();

			for (UUID comp : item.getBundleComponents()) {

				Meal m = productService.findMealEntityById(comp);

				mealsInCombo.add(m);

				components.add(new OrderProductComponent(comp));
			}

			op.setComponents(components);
		}

	}
	
	private void lock() {
		if (cart.getMutable()) {
			cart.setMutable(false);
		}
		this.commit();
	}

	private void unlock() {
		if (!cart.getMutable()) {
			cart.setMutable(true);
		}
		this.commit();
	}
	
	private void commit() {
		persistenceListener.persist(this.cart);
	}
	
	@FunctionalInterface
	public static interface PersistenceListener {
		void persist(Cart cart);
	}
}
