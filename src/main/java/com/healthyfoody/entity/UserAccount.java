package com.healthyfoody.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NaturalId;

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
@Table(name = TableName.USER)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class UserAccount extends BaseEntity {

	@Email
	@NotNull
	@NaturalId(mutable = true)
	@Column(unique = true)
	String email;

	@NotNull
	String password;

	@NotNull
	Boolean emailValidated;

	@NotNull
	Integer statusCode;

	String name;

	@NotNull
	LocalDateTime registeredOn;

	LocalDateTime lastAccessOn;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	Role role;
}
