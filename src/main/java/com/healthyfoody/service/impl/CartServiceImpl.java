package com.healthyfoody.service.impl;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
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
		Customer customer = null;

		if (customerId != null) { //THERE IS CUSTOMER...
			customer = customerService.findEntityById(customerId);
			UUID cartId = customer.getCurrentCart();
			if (cartId != null) { //...WITH CART...
				Optional<Cart> searchResult = cartRepository.findById(cartId);
				if (searchResult.map(Cart::getMutable).orElse(false)) {
					//...VALID AND MUTABLE
					cart = searchResult.get();
				}
			}
		}
		if (cart == null) {
			cart = createCart(customerId);
			if (customer != null) {
				customerService.updateActiveCart(customer, cart.getId());
			}
		}

		CartResponse response = cartMapper.toResponse(cart);
		return response;
	}
	
	private Cart createCart(UUID customerId) {
		Cart cart = new Cart();
		cart.setCustomerId(customerId);
		return save(cart);
	}

	@Override
	public CartResponse addToCart(UUID id, UUID productId, int quantityOrInstance, List<UUID> components, boolean override) {
		CartManager cartManager = getCartManager(id);
		Product product = productService.findEntityById(productId);		
		
		cartManager.addItem(product, quantityOrInstance, components, override);
		
		Cart cart = save(cartManager.getCart());
		CartResponse response = cartMapper.toResponse(cart);
		return response;
	}

	@Override
	public CartResponse deleteFromCart(UUID id, UUID productId, Integer instance) {
		CartManager cartManager = getCartManager(id);
		Product product = productService.findEntityById(productId);	
		
		cartManager.deleteItem(product, instance);

		Cart cart = save(cartManager.getCart());
		CartResponse response = cartMapper.toResponse(cart);
		return response;
	}

	@Override
	public void clearCart(UUID id) {
		CartManager cartManager = getCartManager(id);
		
		cartManager.clear();

		save(cartManager.getCart());
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
