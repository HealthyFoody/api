package com.healthyfoody.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.healthyfoody.dto.request.AddressRequest;
import com.healthyfoody.dto.response.AddressResponse;
import com.healthyfoody.entity.Address;

@Mapper(config = SharedMapperConfig.class)
public interface AddressMapper extends ResponseMapper<AddressResponse, Address>, RequestMapper<AddressRequest, Address> {
	
	@Mapping(target = "customerId", source = "customer.id")
	AddressResponse toResponse(Address entity);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "customer.id", source = "customerId")
	@Mapping(target = "isDefault", constant = "false")
	Address toEntity(AddressRequest request);
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "isDefault", ignore = true)
	@Mapping(target = "customer.id", source = "customerId")
	void updateEntity(AddressRequest request, @MappingTarget Address address);
	
}
