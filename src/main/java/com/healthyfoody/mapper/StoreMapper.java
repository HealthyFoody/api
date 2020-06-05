package com.healthyfoody.mapper;

import org.mapstruct.Mapper;

import com.healthyfoody.dto.response.StoreResponse;
import com.healthyfoody.entity.Store;

@Mapper(config = SharedMapperConfig.class)
public interface StoreMapper extends ResponseMapper<StoreResponse, Store> {

}
