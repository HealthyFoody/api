package com.healthyfoody.api.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.healthyfoody.api.common.TableName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = TableName.MEAL)
public class Meal extends Product {
	
	@NotNull
	@Column(name = "calories_in_kcal")
	Integer calories;
	
	@NotNull
	String ingredients;
}
