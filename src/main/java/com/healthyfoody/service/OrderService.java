package com.healthyfoody.service;

import java.util.UUID;

import org.springframework.data.domain.Page;

import com.healthyfoody.dto.request.OrderRequest;
import com.healthyfoody.dto.response.OrderResponse;
import com.healthyfoody.entity.Order;
import com.healthyfoody.entity.OrderStatus;
import com.healthyfoody.entity.PaymentType;

public interface OrderService extends ResourceService<OrderResponse, Order, UUID> {

    Page<OrderResponse> findCustomerOrders(UUID customerId, int page, int size);

    OrderResponse placeOrder(UUID cartId, OrderRequest orderRequest, PaymentType type);

    OrderResponse payOrder(UUID id, Object token);

    void cancelOrder(UUID id, boolean exceptional);

    void updateStatus(UUID id, OrderStatus newStatus);
}
