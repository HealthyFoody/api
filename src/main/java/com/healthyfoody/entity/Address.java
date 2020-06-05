package com.healthyfoody.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = TableName.ADDRESS)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
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
	String fullAddress;

	@NotNull
	Boolean isDefault;
}