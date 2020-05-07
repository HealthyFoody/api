package com.healthyfoody.dto;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemDto {

	String type;

	UUID productId;

	Integer quantity;
}
