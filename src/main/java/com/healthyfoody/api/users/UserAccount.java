package com.healthyfoody.api.users;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NaturalId;

import com.healthyfoody.api.common.BaseEntity;
import com.healthyfoody.api.common.TableName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = TableName.USER)
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
