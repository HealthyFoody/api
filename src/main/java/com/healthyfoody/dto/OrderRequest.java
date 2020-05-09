package com.healthyfoody.dto;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.healthyfoody.entity.PaymentType;
import com.healthyfoody.validation.annotations.ValidUUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderRequest {

	@ValidUUID
	private UUID customerId;

	@ValidUUID
	private UUID storeId;

	private String address;

	private Double latitude;

	private Double longitude;

	private String programedFor;

	private PaymentType type;

}
