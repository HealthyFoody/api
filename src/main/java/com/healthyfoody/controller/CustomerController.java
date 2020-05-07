package com.healthyfoody.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthyfoody.entity.Customer;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@GetMapping("/get/{id}")
	public Customer findByID(@PathVariable UUID id) {
		return null;
	}

	@PostMapping("/post")
	public Customer addCustomer(@RequestBody Customer entity) {
		return null;
	}

	@PutMapping("/put")
	public Customer editCustomer(@RequestBody Customer entity) {
		return null;
	}

	@PutMapping("delete/{id}")
	public Boolean deactivateCustomer(@PathVariable UUID id) {
		return false;
	}

}
