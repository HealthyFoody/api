package com.healthyfoody.api.location;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.healthyfoody.api.customers.Customer;
import com.healthyfoody.api.common.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = TableName.ADDRESS)
public class Address extends BaseEntity {
	
	String name;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "customer_id")
	Customer customer;
	
	@NotNull
	Double latitude;
	
	@NotNull
	Double longitude;

	@NotNull
	String line1;
	
	String line2;
	
	@NotNull
	String city;
	
	@NotNull
	String district;
	
	@NotNull
	String zipCode;
	
	@NotNull
	Boolean listed;
	
	@NotNull
	Boolean defaultAddress;
}