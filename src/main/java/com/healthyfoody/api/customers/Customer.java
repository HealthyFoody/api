package com.healthyfoody.api.customers;

import java.util.List;

import javax.persistence.*;

import com.healthyfoody.api.address.Address;

@Entity
public class Customer {
	@Id Long id;
	
	String firstName;
	String lastName;
	String email;
	
	List<Address> addresses;
}
