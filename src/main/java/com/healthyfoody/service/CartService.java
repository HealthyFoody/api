package com.healthyfoody.service;

import com.healthyfoody.entity.Cart;
import com.healthyfoody.entity.OrderProduct;
import com.healthyfoody.exception.CartValidationException;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public interface CartService extends CrudService<Cart, UUID> {

    Object createCart(UUID customerId);

    void addToCart(UUID id, UUID productId, int quantityOrInstance, UUID[] components, boolean override);

    void deleteFromCart(UUID id, UUID productId, Integer instance);

    void clearCart(UUID id);

    Cart lockCart(Cart cart);

    Cart unlockCart(Cart cart);

    List<OrderProduct> processCart(UUID id, UUID storeId, LocalTime hour) throws CartValidationException;
}
