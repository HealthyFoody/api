package com.healthyfoody.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.healthyfoody.dto.response.AddressResponse;
import com.healthyfoody.entity.Address;

public class AddressMapperTest {
	
	@Autowired
	AddressMapper mapper = new AddressMapperImpl(new SharedMapper());
	
	@Test
	void test() {		
		Address address = new Address();
		address.setLatitude(80.65);
		AddressResponse response = mapper.toResponse(address);
		
		assertEquals(address.getLatitude(), response.getLatitude());
	}
}
