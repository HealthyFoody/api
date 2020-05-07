package com.healthyfoody.service.impl;

import com.healthyfoody.entity.Address;
import com.healthyfoody.exception.ResourceNotFoundException;
import com.healthyfoody.repository.jpa.AddressRepository;
import com.healthyfoody.service.AddressService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressRepository addressRepository;

    @Override
    public List<Address> findByCustomer(UUID customerId) {
        return addressRepository.findByCustomerId(customerId);
    }

    @Override
    public Address findDefaultAddress(UUID customerId) {
        return addressRepository.findByCustomerId(customerId).stream()
                .filter(x -> x.getDefaultAddress())
                .findFirst().orElse(null);
    }

    @Override
    @Transactional
    public void makeDefaultAddress(UUID id) throws ResourceNotFoundException {
        Address address = addressRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id, Address.class));
        addressRepository.removeDefaultAddress(address.getCustomer().getId());
        address.setDefaultAddress(true);
        addressRepository.save(address);
    }

    @Override
    public Address findById(UUID id) throws ResourceNotFoundException {
        return addressRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id, Address.class));
    }

    @Override
    public Address insert(Address entity) {
        return addressRepository.save(entity);
    }

    @Override
    @Transactional
    public Address update(Address entity) {
        Address oldAddress = findById(entity.getId());

        oldAddress.setName(entity.getName());

        return addressRepository.save(entity);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        addressRepository.deleteById(id);
    }
}
