package com.healthyfoody.service;

import java.util.List;
import java.util.UUID;

import com.healthyfoody.dto.request.AddressRequest;
import com.healthyfoody.dto.response.AddressResponse;
import com.healthyfoody.entity.Address;
import com.healthyfoody.exception.ResourceNotFoundException;

public interface AddressService extends ResourceService<AddressResponse, Address, UUID>, TransactionSevice<AddressResponse, AddressRequest, UUID> {
    List<AddressResponse> findByCustomer(UUID customerId);

    AddressResponse findDefaultAddress(UUID customerId);

    void makeDefaultAddress(UUID id) throws ResourceNotFoundException;
}
