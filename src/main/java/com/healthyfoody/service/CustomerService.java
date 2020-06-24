package com.healthyfoody.service;

import java.util.UUID;

import com.healthyfoody.dto.response.CustomerResponse;
import com.healthyfoody.entity.Customer;

public interface CustomerService extends ResourceService<CustomerResponse, Customer, UUID> {

	CustomerResponse findCustomerByUser(UUID userId);

	void createCustomerProfile(UUID userId);

	void updateActiveCart(Customer customer, UUID cartId);
}
