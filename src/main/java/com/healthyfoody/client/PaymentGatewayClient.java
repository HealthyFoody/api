package com.healthyfoody.client;

import com.healthyfoody.entity.Order;

import java.util.Map;

public interface PaymentGatewayClient {

    Map<String, Object> makePayment(Order order, Map<String, Object> token) throws Exception;

    Map<String, Object> refundPayment(Order order);
}
