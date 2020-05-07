package com.healthyfoody.controller;

import com.healthyfoody.entity.Customer;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@GetMapping("/get/{id}")
	Customer findByID(@PathVariable UUID id) {
		return null;
	}
	
	@PostMapping("/post")
	Customer addCustomer(@RequestBody Customer entity) {
		return null;
	}
	
	@PutMapping("/put")
	Customer editCustomer(@RequestBody Customer entity) {
		return null;
	}
	
	@PutMapping("delete/{id}")
	Boolean deactivateCustomer(@PathVariable UUID id) {
		return false;
	}
	
}
