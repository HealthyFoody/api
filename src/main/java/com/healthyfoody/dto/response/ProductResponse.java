package com.healthyfoody.dto.response;

import java.math.BigDecimal;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponse extends BaseProductResponse {
	protected LocalTime toHour; 
	protected String description;
    protected BigDecimal price;
	protected String imageUrl;
	protected String type;
}


