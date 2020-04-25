package com.healthyfoody.api.product.entity;

import java.io.Serializable;
import java.util.UUID;

import lombok.Data;
import org.hibernate.annotations.Type;

@Data
public class StockId implements Serializable {

	@Type(type = "uuid-char")
	UUID product;

	@Type(type = "uuid-char")
	UUID store;
}
