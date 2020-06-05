package com.healthyfoody.mapper;

import java.util.List;
import java.util.Set;

import org.mapstruct.*;

import com.healthyfoody.dto.response.BaseProductResponse.*;
import com.healthyfoody.dto.response.OrderResponse;
import com.healthyfoody.entity.Order;
import com.healthyfoody.entity.OrderProduct;
import com.healthyfoody.entity.OrderProductComponent;

@Mapper(config = SharedMapperConfig.class)
public interface OrderMapper extends ResponseMapper<OrderResponse, Order> {
	
	@Mapping(target = "customerId", source = "customer.id")
	@Mapping(target = "courierId", source = "courier.id")
	@Mapping(target = "products", source = "orderProducts")
	OrderResponse toResponse(Order entity);
	
	@IterableMapping(qualifiedByName = "resolveOrderProduct")
	List<ItemDto> mapOrderProductList(List<OrderProduct> entities);
	
	@Named("resolveOrderProduct")
	default ItemDto mapOrderProducts(OrderProduct entity) {
		if (entity == null)
				return null;
		if (entity.getIsCombo()) {
			return mapOrderCombo(entity);
		}
		else {
			return mapOrderMeal(entity);
		}
	}
	
	@Mapping(target = "name", source = "product.name")
	@Mapping(target = "id", source = "product.id")
	MealItemDto mapOrderMeal(OrderProduct entity);
	
	@Mapping(target = "name", source = "product.name")
	@Mapping(target = "id", source = "product.id")
	ComboItemDto mapOrderCombo(OrderProduct entity);
	
	List<ComponentDto> mapComponentList(Set<OrderProductComponent> components);
	
	@Mapping(target = "id", source = "mealId")
	@Mapping(target = "name", constant = "meal")
	ComponentDto mapComponent(OrderProductComponent component);
}
