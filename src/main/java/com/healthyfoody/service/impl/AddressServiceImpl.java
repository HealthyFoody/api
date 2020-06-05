package com.healthyfoody.service.impl;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthyfoody.dto.request.AddressRequest;
import com.healthyfoody.dto.response.AddressResponse;
import com.healthyfoody.entity.Address;
import com.healthyfoody.exception.ResourceNotFoundException;
import com.healthyfoody.mapper.AddressMapper;
import com.healthyfoody.repository.jpa.AddressRepository;
import com.healthyfoody.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressRepository addressRepository;
    
    @Autowired
    AddressMapper addressMapper;

    @Override
    public List<AddressResponse> findByCustomer(UUID customerId) {
        List<Address> addressList = addressRepository.findByCustomerId(customerId);
        return addressMapper.mapResponseList(addressList);
    }

    @Override
    public AddressResponse findDefaultAddress(UUID customerId) {
        Address address = addressRepository.findByCustomerId(customerId).stream()
                .filter(x -> x.getIsDefault())
                .findFirst().orElse(null);
        return addressMapper.toResponse(address);
    }

    @Override
    @Transactional
    public void makeDefaultAddress(UUID id) throws ResourceNotFoundException {
        Address address = findEntityById(id);
        addressRepository.removeDefaultAddress(address.getCustomer().getId());
        address.setIsDefault(true);
        addressRepository.save(address);
    }

    @Override
    public AddressResponse findById(UUID id) throws ResourceNotFoundException {
        Address address = findEntityById(id);
        return addressMapper.toResponse(address);
    }


    @Transactional
    public void deleteEntity(UUID id) {
        addressRepository.deleteById(id);
    }

	@Override
	public Address findEntityById(UUID id) throws ResourceNotFoundException {
		return addressRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id, Address.class));
	}

	@Override
	public AddressResponse insert(AddressRequest request) {
		
		Address address = addressMapper.toEntity(request);
		
		Address result = addressRepository.save(address);
		return addressMapper.toResponse(result);
	}

	@Override
	public AddressResponse update(AddressRequest request, UUID id) {
		Address address = findEntityById(id);
		addressMapper.updateEntity(request, address);
		
		Address result = addressRepository.save(address);
		return addressMapper.toResponse(result);
	}

	@Override
	public void delete(UUID id) {
		addressRepository.deleteById(id);
	}
}
