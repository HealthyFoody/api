package com.healthyfoody.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.healthyfoody.dto.response.CustomerResponse;
import com.healthyfoody.entity.Customer;

@Mapper(config = SharedMapperConfig.class)
public interface CustomerMapper extends ResponseMapper<CustomerResponse, Customer> {
	
	@Mapping(target = "userId", source = "user.id")
	@Mapping(target = "firstName", source = "user.firstName")
	@Mapping(target = "lastName", source = "user.lastName")
	CustomerResponse toResponse(Customer entity);
}
