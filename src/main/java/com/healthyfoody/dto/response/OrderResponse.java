package com.healthyfoody.dto.response;

import java.math.BigDecimal;
import java.util.List;

import com.healthyfoody.dto.ApiResponse;
import com.healthyfoody.dto.response.BaseProductResponse.ItemDto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderResponse implements ApiResponse {
	
	private String id;
	
	private String customerId;
	
	private List<ItemDto> products;
	
	private String courierId;
	
	private String status;
	
	private String address;

	private Double latitude;

	private Double longitude;
	
	private String paymentType;

	private String customerRequest;
	
	private BigDecimal price;
}
