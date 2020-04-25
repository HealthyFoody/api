package com.healthyfoody.api.product.controller.form;

import java.util.List;

import lombok.Data;

@Data
public class StockRequestForm {
	List<String> itemIds;
	
	String storeId;
	
	String hour;
}
