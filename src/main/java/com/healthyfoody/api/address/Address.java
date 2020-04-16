package com.healthyfoody.api.address;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Address {
	@Id Long id;
	
	String name;
	
	Double latitude;
	Double Longitude;
	
	Boolean defaultAddress;
}