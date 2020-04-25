package com.healthyfoody.api.location;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/location")
public class LocationController {
	
	@Autowired
	AddressService addressService;
	
	@Autowired
	StoreService storeService;
	
	@GetMapping("/get")
	public ResponseEntity<?> addressesList(@RequestParam("customer") UUID customerId){

		List<Address> list = addressService.findByCustomer(customerId);
		
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<?> findById(@PathVariable UUID id) {
		
		return addressService.findById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping("/post")
	public ResponseEntity<?> addAddress(@RequestBody Address address) {

		System.out.println(address);

		return ResponseEntity.ok(addressService.saveAddress(address));
	}
	
	@PutMapping("/put")
	public ResponseEntity<?> editAddress(@RequestBody Address address) {

		
		return ResponseEntity.ok(addressService.editAddress(address));
	}
	
	/*FIXME: no borra las entradas en la bd. ¿delete o put?*/
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteMapping(@PathVariable UUID id) {
	
		return ResponseEntity.ok(addressService.deleteAddress(id));
	}
	
	@GetMapping("/store")
	public ResponseEntity<?> findNearbyStores(Address address) {

		return ResponseEntity.ok(storeService.findNearbyStores(address));
	}
}
