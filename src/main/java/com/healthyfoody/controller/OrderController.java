package com.healthyfoody.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.healthyfoody.dto.request.OrderRequest;
import com.healthyfoody.mapper.OrderMapper;
import com.healthyfoody.service.OrderService;
import com.healthyfoody.validation.annotations.GUID;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	OrderMapper orderMapper;

	@Autowired
	OrderService orderService;

	@GetMapping("/invoice")
	public ResponseEntity<?> getAllCustomerOrder(@Valid @RequestParam("customer") UUID customerId, @RequestParam(defaultValue = "0") Integer page){

		return ResponseEntity.ok(orderService.findCustomerOrders(customerId, page, 15));
	}

	@GetMapping("/invoice/{id}")
	public ResponseEntity<?> getOrderById(@Valid @PathVariable("id") UUID id){

		return ResponseEntity.ok(orderService.findById(id));
	}

	@PostMapping("/place/{cartId}")
	public ResponseEntity<?> placeOrder(
			@PathVariable @GUID String cartId,
			@Valid @RequestBody OrderRequest form){
		//TODO: FIX
		return ResponseEntity.ok(null);
	}
}
