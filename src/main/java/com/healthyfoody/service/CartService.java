package com.healthyfoody.service;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import com.healthyfoody.dto.response.CartResponse;
import com.healthyfoody.entity.OrderProduct;
import com.healthyfoody.entity.Store;
import com.healthyfoody.entity.redis.Cart;

public interface CartService extends ResourceService<CartResponse, Cart, UUID> {

    CartResponse obtainCustomerCart(UUID customerId);

    CartResponse addToCart(UUID id, UUID productId, int quantityOrInstance, List<UUID> components, boolean override);

    CartResponse deleteFromCart(UUID id, UUID productId, Integer instance);

    void clearCart(UUID id);

    List<OrderProduct> processCart(UUID id, Store store, LocalTime programmedHour);
}
