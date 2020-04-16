package com.healthyfoody.api.address;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address")
public class AddressController {
	
	@GetMapping("/get")
	List<Address> addressesByCustomer(@RequestParam("customer") Long customerId){
		return null;
	}
	
	@GetMapping("/get/{id}")
	Address findById(@PathVariable Long id) {
		return null;
	}
	
	@PostMapping("/post")
	Address addAddress(@RequestBody Address entity) {
		return null;
	}
	
	@PutMapping("/put")
	Address editAddress(@RequestBody Address entity) {
		return null;
	}
	
	@DeleteMapping("/delete/{id}")
	Boolean deleteMapping(@PathVariable Long id) {
		return false;
	}
}
