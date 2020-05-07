package com.healthyfoody.service.impl;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthyfoody.entity.Cart;
import com.healthyfoody.entity.CartCombo;
import com.healthyfoody.entity.CartMeal;
import com.healthyfoody.entity.Combo;
import com.healthyfoody.entity.Meal;
import com.healthyfoody.entity.OrderProduct;
import com.healthyfoody.entity.OrderProductComponent;
import com.healthyfoody.entity.Product;
import com.healthyfoody.entity.ProductType;
import com.healthyfoody.exception.CartValidationException;
import com.healthyfoody.exception.ResourceNotFoundException;
import com.healthyfoody.repository.redis.CartRepository;
import com.healthyfoody.service.CartService;
import com.healthyfoody.service.ProductService;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	CartRepository cartRepository;

	@Autowired
	ProductService productService;

	@Override
	@Transactional
	public Object createCart(UUID customerId) {

		// Validate Customer

		Cart cart = new Cart();
		cart = cartRepository.save(cart);
		return cart;
	}

	@Override
	@Transactional
	public void addToCart(UUID id, UUID productId, int quantityOrInstance, UUID[] components, boolean override) {
		Cart cart = checkCart(id);

		// FIXME: replace assert statement
		assert quantityOrInstance > 0 : "Error al agregar producto a la orden";

		Product product = productService.findById(productId);
		if (product instanceof Meal) {

			CartMeal meal = new CartMeal(productId, quantityOrInstance);
			Set<CartMeal> meals = cart.getMeals();

			if (override)
				meals.remove(meal);
			meals.add(meal);
		}
		if (product instanceof Combo) {

			// FIXME: replace assert statement
			assert components.length > 0 : "Error al agregar el combo";

			CartCombo combo = new CartCombo(productId, quantityOrInstance, Arrays.asList(components));
			Set<CartCombo> combos = cart.getCombos();

			if (override)
				combos.remove(combo);
			combos.add(combo);
		}
		cartRepository.save(cart);
	}

	@Override
	@Transactional
	public void deleteFromCart(UUID id, UUID productId, Integer instance) {
		Cart cart = checkCart(id);

		Product product = productService.findById(productId);
		if (product instanceof Meal) {
			CartMeal meal = new CartMeal(productId, null);
			cart.getMeals().remove(meal);

		}
		if (product instanceof Combo) {
			CartCombo combo = new CartCombo(productId, instance, null);
			cart.getCombos().remove(combo);
		}
		cartRepository.save(cart);
	}

	@Override
	@Transactional
	public void clearCart(UUID id) {
		Cart cart = checkCart(id);

		cart.setCombos(new HashSet<>());
		cart.setMeals(new HashSet<>());

		cartRepository.save(cart);
	}

	@Override
	public Cart lockCart(Cart cart) {
		if (cart.getMutable()) {
			cart.setMutable(false);
			cart = cartRepository.save(cart);
		}
		return cart;
	}

	@Override
	public Cart unlockCart(Cart cart) {
		if (!cart.getMutable()) {
			cart.setMutable(true);
			cart = cartRepository.save(cart);
		}
		return cart;
	}

	// FIXME: kill me plz
	private void checkCartForErrors(Cart cart, UUID storeId, LocalTime hour) throws CartValidationException {

		boolean hasError = false;
		Map<String, List<String>> errorDetails = new HashMap<>();

		List<UUID> validatedProducts = new ArrayList<>();

		for (CartMeal meal : cart.getMeals()) {
			// VALIDA SI UN PRODUCTO ESTÁ A LA VENTA, TIENE STOCK Y ESTÁ DENTRO DEL HORARIO
			// ESTABLECIDO
			List<String> productError = productService.validationReport(meal.getProductId(), true, storeId, hour);
			if (!productError.isEmpty()) {
				hasError = true;
				errorDetails.put(meal.getProductId().toString(), productError);
			}
			validatedProducts.add(meal.getProductId());
		}

		for (CartCombo combo : cart.getCombos()) {

			// VALIDA COMBO SI NO FUE REVISADO ANTES
			if (!validatedProducts.contains(combo.getComboId())) {
				List<String> productError = productService.validationReport(combo.getComboId(), true, storeId, hour);
				if (!productError.isEmpty()) {
					hasError = true;
					errorDetails.put(combo.getComboId().toString(), productError);
				}
				validatedProducts.add(combo.getComboId());
			}

			// VALIDA ITEMS DEL COMBO QUE NO FUERON VALIDADOS
			for (UUID component : combo.getBundleComponents()) {
				if (!validatedProducts.contains(component)) {
					List<String> componentError = productService.validationReport(component, false, storeId, hour);
					if (!componentError.isEmpty()) {
						hasError = true;
						errorDetails.put(component.toString(), componentError);
					}
				}
			}

			// VALIDA QUE LA COMBINACION DE PRODUCTOS DE UN COMBO ES VALIDO
			if (!productService.validateCombination(combo.getComboId(), combo.getBundleComponents())) {
				hasError = true;
				List<String> comboError = errorDetails.computeIfAbsent(combo.getComboId().toString(),
						k -> new ArrayList<>());
				comboError.add("combinacion no valida de componentes de un combo");
			}
		}

		if (hasError)
			throw new CartValidationException(errorDetails);
	}

	@Override
	public List<OrderProduct> processCart(UUID id, UUID storeId, LocalTime hour) throws CartValidationException {

		Cart cart = findById(id);

		// EVITA QUE EDITE EL CARRITO MIENTRAS PROCESA LA ORDEN
		cart = lockCart(cart);

		// FIXME: eliminar llamado a metodo e incluir funcionalidad acá
		try {
			checkCartForErrors(cart, storeId, hour);
		} catch (Exception e) {
			unlockCart(cart);
			throw e;
		}

		List<OrderProduct> orderProducts = new ArrayList<>();

		for (CartMeal item : cart.getMeals()) {
			OrderProduct op = new OrderProduct();
			Product p = productService.findById(item.getProductId());

			op.setProduct(p);
			op.setPrice(p.getPrice());
			op.setQuantity(item.getQuantity());
			op.setInstance(1);
			op.setIsCombo(false);

			orderProducts.add(op);
		}

		for (CartCombo item : cart.getCombos()) {
			OrderProduct op = new OrderProduct();
			Combo c = (Combo) productService.findTypedById(item.getComboId(), ProductType.COMBO);

			op.setProduct(c);
			op.setPrice(c.getPrice());
			op.setQuantity(1);
			op.setInstance(item.getInstance());
			op.setIsCombo(true);

			List<OrderProductComponent> components = new ArrayList<>();
			for (UUID comp : item.getBundleComponents()) {
				components.add(new OrderProductComponent(comp));
			}

			op.setComponents(components);
		}

		return orderProducts;
	}

	private Cart checkCart(UUID id) {
		Cart cart = findById(id);
		if (!cart.getMutable())
			throw new RuntimeException("Este carrito no puede ser editado");
		return cart;
	}

	@Override
	public Cart findById(UUID id) throws ResourceNotFoundException {
		return cartRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id, Cart.class));
	}
}
