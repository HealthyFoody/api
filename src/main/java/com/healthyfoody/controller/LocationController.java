package com.healthyfoody.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.healthyfoody.dto.request.AddressRequest;
import com.healthyfoody.entity.Address;
import com.healthyfoody.service.AddressService;
import com.healthyfoody.service.StoreService;
import com.healthyfoody.validation.annotations.GUID;

@RestController
@RequestMapping("/location")
public class LocationController {
	
	@Autowired
	AddressService addressService;
	
	@Autowired
	StoreService storeService;

	@GetMapping("/addresses")
	public ResponseEntity<?> addressesList(
			@RequestParam("customer") @GUID String customerId){

		return ResponseEntity.ok(addressService.findByCustomer(UUID.fromString(customerId)));
	}
	
	@GetMapping("/addresses/{id}")
	public ResponseEntity<?> findById(
			@PathVariable @GUID String id) {
		
		return ResponseEntity.ok(addressService.findById(UUID.fromString(id)));
	}
	
	@PostMapping("/addresses")
	public ResponseEntity<?> addAddress(
			@RequestBody AddressRequest address,
			@RequestParam(name = "default", defaultValue = "false") Boolean isDefault) {

		return ResponseEntity.ok(addressService.insert(address));
	}
	
	@PutMapping("/addresses/{id}")
	public ResponseEntity<?> editAddress(
			@PathVariable("id") @GUID String id,
			@RequestBody AddressRequest address,
			@RequestParam(name = "default", defaultValue = "false") Boolean isDefault) {

		return ResponseEntity.ok(addressService.update(address, UUID.fromString(id)));
	}

	@PatchMapping("/addresses/{id}/default")
	public ResponseEntity<?> makeDefaultAddress(
			@PathVariable @GUID String id) {

		addressService.makeDefaultAddress(UUID.fromString(id));
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/addresses/{id}")
	public ResponseEntity<?> deleteAddress(
			@PathVariable @GUID String id) {
		addressService.delete(UUID.fromString(id));
		return ResponseEntity.ok().build();
	}

	@GetMapping("/stores")
	public ResponseEntity<?> findNearbyStores(
			@RequestBody Address address) {

		//TODO: implement controller method
		return null;
	}
}
