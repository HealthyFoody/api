package com.healthyfoody.api.orders;

import java.io.Serializable;
import java.util.UUID;

import lombok.Data;

@Data
public class OrderDetailId implements Serializable {

	UUID order;
	
	UUID product;
}
