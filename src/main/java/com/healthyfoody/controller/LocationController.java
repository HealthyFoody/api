package com.healthyfoody.controller;

import com.healthyfoody.entity.Address;
import com.healthyfoody.validation.ValidUUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/location")
public class LocationController {
	

	@GetMapping("/addresses")
	public ResponseEntity<?> addressesList(
			@RequestParam("customer") @ValidUUID String customerId){

		//TODO: implement controller method
		return null;
	}
	
	@GetMapping("/addresses/{id}")
	public ResponseEntity<?> findById(
			@PathVariable @ValidUUID String id) {
		
		//TODO: implement controller method
		return null;
	}
	
	@PostMapping("/addresses")
	public ResponseEntity<?> addAddress(
			@RequestBody Address address) {

		//TODO: implement controller method
		return null;
	}
	
	@PutMapping("/addresses/{id}")
	public ResponseEntity<?> editAddress(
			@PathVariable("id") @ValidUUID String id,
			@RequestBody Address address) {

		//TODO: implement controller method
		return null;
	}

	@PatchMapping("/addresses/{id}/default")
	public ResponseEntity<?> makeDefaultAddress(
			@PathVariable @ValidUUID String id,
			@RequestBody Address address) {

		//TODO: implement controller method
		return null;
	}

	@DeleteMapping("/addresses/{id}")
	public ResponseEntity<?> deleteAddress(
			@PathVariable@ValidUUID String id) {
		//TODO: implement controller method
		return null;
	}

	@GetMapping("/stores")
	public ResponseEntity<?> findNearbyStores(
			@RequestBody Address address) {

		//TODO: implement controller method
		return null;
	}
}
