package com.healthyfoody.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

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

	String phone;

	@OneToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "user_id", unique = true, nullable = true)
	UserAccount user;

	@Column(name = "current_cart")
	UUID currentCart;
}
