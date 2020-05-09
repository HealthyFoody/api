package com.healthyfoody.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = TableName.CUSTOMER)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Customer extends BaseEntity {

	String firstName;

	String lastName;

	@OneToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "user_id", unique = true, nullable = true)
	UserAccount user;

	@Column(name = "current_cart")
	UUID currentCart;
}
