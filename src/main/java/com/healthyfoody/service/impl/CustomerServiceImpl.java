package com.healthyfoody.service.impl;

import com.healthyfoody.entity.Customer;
import com.healthyfoody.exception.ResourceNotFoundException;
import com.healthyfoody.service.CustomerService;

import java.util.UUID;

public class CustomerServiceImpl implements CustomerService {
    @Override
    public Customer createCustomer(UUID userId) {
        return null;
    }

    @Override
    public Customer findById(UUID id) throws ResourceNotFoundException {
        return null;
    }

}
