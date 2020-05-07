package com.healthyfoody.service;

import com.healthyfoody.entity.Customer;

import java.util.UUID;

public interface CustomerService extends CrudService<Customer, UUID> {

    Customer createCustomer(UUID userId);
}
