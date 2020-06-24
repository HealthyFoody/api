package com.healthyfoody.service.impl;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthyfoody.dto.response.CartResponse;
import com.healthyfoody.entity.*;
import com.healthyfoody.entity.redis.Cart;
import com.healthyfoody.exception.ResourceNotFoundException;
import com.healthyfoody.mapper.CartMapper;
import com.healthyfoody.repository.redis.CartRepository;
import com.healthyfoody.service.CartService;
import com.healthyfoody.service.CustomerService;
import com.healthyfoody.service.ProductService;
import com.healthyfoody.util.CartManager;
import com.healthyfoody.util.CartManager.PersistenceListener;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	CartRepository cartRepository;

	@Autowired
	ProductService productService;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	CartMapper cartMapper;

	@Override
	public CartResponse obtainCustomerCart(UUID customerId) {
		Cart cart = null;
		Customer customer = customerService.findEntityById(customerId);
		UUID cartId = customer.getCurrentCart();
		if (cartId != null) {
			cart = findEntityById(cartId);
			if (!cart.getMutable())
				cart = createCart();
		} else {
			cart = createCart();
			customerService.updateActiveCart(customer, cart.getId());
		}
		CartResponse response = cartMapper.toResponse(cart);
		response.setCustomerId(customerId.toString());
		return response;
	}
	
	private Cart createCart() {
		Cart cart = new Cart();
		return cartRepository.save(cart);
	}

	@Override
	public void addToCart(UUID id, UUID productId, int quantityOrInstance, List<UUID> components, boolean override) {
		CartManager cart = getCartManager(id);
		Product product = productService.findEntityById(productId);		
		
		cart.addItem(product, quantityOrInstance, components, override);
		
		cartRepository.save(cart.getCart());
	}

	@Override
	public void deleteFromCart(UUID id, UUID productId, Integer instance) {
		CartManager cart = getCartManager(id);
		Product product = productService.findEntityById(productId);	
		
		cart.deleteItem(product, instance);
		
		cartRepository.save(cart.getCart());
	}

	@Override
	public void clearCart(UUID id) {
		CartManager cart = getCartManager(id);
		
		cart.clear();

		cartRepository.save(cart.getCart());
	}

	@Override
	public Cart findEntityById(UUID id) throws ResourceNotFoundException {
		return cartRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id, Cart.class));
	}

	@Transactional
	public Cart save(Cart entity) {
		return cartRepository.save(entity);
	}

	@Transactional
	public void delete(UUID id) {
		cartRepository.deleteById(id);
	}
	
	private CartManager getCartManager(UUID cartId) {
		Cart entity = findEntityById(cartId);
		PersistenceListener listener = new PersistenceListener() {
			@Override
			public void persist(Cart cart) { save(cart); }
		};
		
		return new CartManager(entity, productService, listener);
	}

	@Override
	public List<OrderProduct> processCart(UUID id, Store store, LocalTime programmedHour) {
		CartManager cart = getCartManager(id);
		cart.setStore(store);
		cart.setProgrammedHour(programmedHour);
		
		return cart.processCart();
	}
}
