package com.healthyfoody.entity;

import java.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NaturalId;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = TableName.USER)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class UserAccount extends BaseEntity {

	@Email
	@NotNull
	@NaturalId(mutable = true)
	@Column(unique = true)
	String email;

	@NotNull
	String password;
	
	String firstName;

	String lastName;

	@NotNull
	Boolean emailValidated;

	@NotNull
	@Enumerated
	AccountStatus statusCode;

	@NotNull
	LocalDateTime registeredOn;

	LocalDateTime lastAccessOn;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	Role role;

	@OneToOne(mappedBy = "user")
	Customer customer;
}
