package com.healthyfoody.dto.request;

import java.time.LocalDateTime;
import java.util.UUID;

import com.healthyfoody.dto.ApiRequest;
import com.healthyfoody.entity.PaymentType;
import com.healthyfoody.validation.annotations.GUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest implements ApiRequest {

	@GUID
	private UUID customerId;

	@GUID
	private UUID storeId;

	private String address;

	private Double latitude;

	private Double longitude;

	private LocalDateTime programedFor;

	private PaymentType type;

}
