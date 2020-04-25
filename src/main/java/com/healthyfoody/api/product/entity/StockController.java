package com.healthyfoody.api.product.entity;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public class StockController {

	@GetMapping("/verify")
	public ResponseEntity<?> verifyStock(/*FIXME: dataType*/Object address, Long storeId, Integer[] productIds) {
		//TODO: implement controller method
		return null;
	}
}
