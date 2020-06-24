package com.healthyfoody.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.healthyfoody.dto.response.CustomerResponse;
import com.healthyfoody.entity.Customer;

@Mapper(config = SharedMapperConfig.class)
public interface CustomerMapper extends ResponseMapper<CustomerResponse, Customer> {
	
	@Mapping(target = "statusCode", source = "user.statusCode")
	@Mapping(target = "role", constant = "customer")
	@Mapping(target = "registeredOn", source = "user.registeredOn")
	@Mapping(target = "lastAccessOn", source = "user.lastAccessOn")
	@Mapping(target = "emailValidated", source = "user.emailValidated")
	@Mapping(target = "email", source = "user.email")
	@Mapping(target = "firstName", source = "user.firstName")
	@Mapping(target = "lastName", source = "user.lastName")
	CustomerResponse toResponse(Customer entity);
}
