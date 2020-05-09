package com.healthyfoody.mapper;

import com.healthyfoody.dto.OrderRequest;
import com.healthyfoody.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { UtilMapper.class })
public interface OrderMapper {

    @Mapping(target = "customer.id", source = "customerId")
    @Mapping(target = "store.id", source = "storeId")
    @Mapping(target = "programmedFor", source = "programedFor", dateFormat = "yyyy-MM-dd HH:mm")
    Order requestToEntity(OrderRequest request);
}
