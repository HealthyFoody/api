package com.healthyfoody.api.product.controller.resource;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@Builder
@JsonInclude(content = Include.NON_NULL)
public class ProductStockModel {
	UUID itemId;
	
	Boolean inStock;
	
	Boolean inTimeframe;
	
	String message;
}
