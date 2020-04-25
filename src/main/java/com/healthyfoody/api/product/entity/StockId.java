package com.healthyfoody.api.product.entity;

import java.io.Serializable;
import java.util.UUID;

import lombok.Data;

@Data
public class StockId implements Serializable {
	
	UUID product;
	
	UUID store;
}
