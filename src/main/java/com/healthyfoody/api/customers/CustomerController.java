package com.healthyfoody.api.customers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@GetMapping("/get/{id}") 
	Customer findByID(@PathVariable Long id) {
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
	Boolean deactivateCustomer(@PathVariable Long id) {
		return false;
	}
	
}
