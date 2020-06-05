package com.healthyfoody.mapper;

import org.mapstruct.Mapper;

import com.healthyfoody.dto.response.CourierResponse;
import com.healthyfoody.entity.Courier;

@Mapper(config = SharedMapperConfig.class)
public interface CourierMapper extends ResponseMapper<CourierResponse, Courier> {
	
}
