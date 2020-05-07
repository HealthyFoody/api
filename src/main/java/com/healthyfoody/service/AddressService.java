package com.healthyfoody.service;

import com.healthyfoody.entity.Address;
import com.healthyfoody.exception.ResourceNotFoundException;

import java.util.List;
import java.util.UUID;

public interface AddressService extends CrudService<Address, UUID> {
    List<Address> findByCustomer(UUID customerId);

    Address findDefaultAddress(UUID customerId);

    void makeDefaultAddress(UUID id) throws ResourceNotFoundException;
}
