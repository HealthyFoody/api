package com.healthyfoody.api.location;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
	
	@Autowired
	AddressRepository addressRepository;

	public List<Address> findByCustomer(UUID customerId) {
		
		return addressRepository.findByCustomerId(customerId);
	}
	
	public Optional<Address> findById(UUID id) {
		
		return addressRepository.findById(id);
		
	}
	
	@Transactional
	public Address saveAddress(Address entity) {
		
		entity.setId(UUID.randomUUID());
		return addressRepository.save(entity);
	}
	
	@Transactional
	public Address editAddress(Address entity) {
		
		Address address = addressRepository.findById(entity.getId()).orElse(null);
	
		if (address != null) {
			address.setName(entity.getName());
			address.setListed(entity.getListed());
			address.setLatitude(entity.getLatitude());
			address.setLongitude(entity.getLongitude());
			address.setCity(entity.getCity());
			address.setLine1(entity.getLine1());
			address.setLine2(entity.getLine2());
			address.setZipCode(entity.getZipCode());
			
			address.setDefaultAddress(entity.getDefaultAddress());
			
			if (address.getDefaultAddress())
				setDefaultAddress(address.getId());		
		}
		
		return address;
	}
	
	@Transactional
	public UUID deleteAddress(UUID id) {
		addressRepository.deleteById(id);
		return id;
	}
	
	private void setDefaultAddress(UUID customerId) {
		//TODO: change all addresses from the same customer to not default
		addressRepository.removeDefaultAddress(customerId);
	}


}

