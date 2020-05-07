package com.healthyfoody.controller;

import com.healthyfoody.dto.OrderForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@GetMapping("/get")
	public ResponseEntity<?> getAllCustomerOrder(@Valid @RequestParam("customer") UUID customerId, @RequestParam(defaultValue = "0") Integer page){

		//TODO: implement controller method
		return null;
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<?> getOrderById(@Valid @PathVariable("id") UUID id){

		//TODO: implement controller method
		return null;
	}

	@PostMapping(Endpoint.PLACE_ORDER)
	public ResponseEntity<?> placeOrder(@Valid @RequestBody OrderForm form){

		//TODO: implement controller method
		return null;
	}

	@PutMapping("/put")
	public ResponseEntity<?> modifyOrder(@Valid @RequestBody OrderForm form){

		//TODO: implement controller method
		return null;
	}

	private static class Endpoint {
		public static final String PLACE_ORDER = "/post";
	}
}
