package com.healthyfoody.service;

import com.healthyfoody.entity.Order;
import com.healthyfoody.entity.OrderStatus;
import com.healthyfoody.entity.PaymentType;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface OrderService extends CrudService<Order, UUID> {

    Page<Order> findCustomerOrders(UUID customerId, int page, int size);

    Order placeOrder(UUID cartId, Order orderRequest, PaymentType type);

    Order payOrder(UUID id, Object token);

    void cancelOrder(UUID id, boolean exceptional);

    void updateStatus(UUID id, OrderStatus newStatus);
}
