package com.healthyfoody.service.impl;

import java.time.LocalTime;
import java.util.*;

import javax.transaction.Transactional;

import com.healthyfoody.entity.*;
import com.healthyfoody.service.StoreService;
import com.healthyfoody.service.condition.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	@Autowired
	StoreService storeService;

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
	public void addToCart(UUID id, UUID productId, int quantityOrInstance, List<UUID> components, boolean override) {
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
			assert components.size() > 0 : "Error al agregar el combo";

			CartCombo combo = new CartCombo(productId, quantityOrInstance, components);
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
	public void lockCart(Cart cart) {
		if (cart.getMutable()) {
			cart.setMutable(false);
			cart = cartRepository.save(cart);
		}
	}

	@Override
	public void unlockCart(Cart cart) {
		if (!cart.getMutable()) {
			cart.setMutable(true);
			cart = cartRepository.save(cart);
		}
	}

	@Override
	public List<OrderProduct> processCart(UUID id, UUID storeId, LocalTime hour) throws CartValidationException {


		List<ConditionValidator> validations = new ArrayList<>();

		Cart cart = findById(id);
		Store store = storeService.findById(storeId);



		// EVITA QUE EDITE EL CARRITO MIENTRAS PROCESA LA ORDEN
		lockCart(cart);

		List<OrderProduct> orderProducts = new ArrayList<>();

		for (CartMeal item : cart.getMeals()) {
			OrderProduct op = new OrderProduct();
			Product p = productService.findById(item.getProductId());

			// VALIDA SI UN PRODUCTO ESTÁ A LA VENTA, TIENE STOCK Y ESTÁ DENTRO DEL HORARIO ESTABLECIDO
			validations.add(new ProductIsListedValidation(p));
			validations.add(new ProductInStockValidator(p, store));
			validations.add(new OnSaleHoursValidator(p,hour));

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

			// TODO: VALIDAR COMBO SI NO FUE REVISADO ANTES (SOLUCION: Set en lugar de List)
			validations.add(new ProductIsListedValidation(c));
			validations.add(new ProductInStockValidator(c, store));
			validations.add(new OnSaleHoursValidator(c,hour));
			// TODO: añadir validacion por día


			op.setProduct(c);
			op.setPrice(c.getPrice());
			op.setQuantity(1);
			op.setInstance(item.getInstance());
			op.setIsCombo(true);

			List<OrderProductComponent> components = new ArrayList<>();

			List<Meal> mealsInCombo = new ArrayList<>();

			for (UUID comp : item.getBundleComponents()) {

				Meal m = (Meal) productService.findTypedById(comp, ProductType.MEAL);

				mealsInCombo.add(m);

				// VALIDA SI EL PRODUCTO FORMA PARTE DE ESTE COMBO Y SI HAY STOCK
				validations.add(new ComboContainsMealValidator(c, m));
				validations.add(new ProductInStockValidator(m, store));

				components.add(new OrderProductComponent(comp));
			}

			//VALIDA SI LA COMBINACION ES VALIDA (NO MÁS DE UN PRODUCTO DEL MISMO GRUPO
			validations.add(new OnePerGroupValidator(c,mealsInCombo));

			op.setComponents(components);
		}

		List<ValidationError> errors = new ArrayList<>();

		for (ConditionValidator v : validations) {
			Optional<ValidationError> error = v.validate();

			error.ifPresent(errors::add);
		}

		if (!errors.isEmpty())
			throw new CartValidationException(errors);

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
