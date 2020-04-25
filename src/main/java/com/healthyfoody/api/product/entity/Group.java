package com.healthyfoody.api.product.entity;

import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.healthyfoody.api.common.BaseEntity;
import com.healthyfoody.api.common.TableName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = TableName.GROUP, uniqueConstraints = @UniqueConstraint(columnNames = { "combo_id", "name" }))
public class Group extends BaseEntity {

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "combo_id")
	Combo combo; 
	
	@NotNull
	String name;

	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = TableName.COMBO_GROUP_MEAL,
			joinColumns = { @JoinColumn(name = "group_id") }, 
			inverseJoinColumns = { @JoinColumn(name = "meal_id") })
	List<Meal> meals;
}
