package com.healthyfoody.entity;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Table(name = TableName.MEAL_GROUP, uniqueConstraints = @UniqueConstraint(columnNames = { "combo_id", "name" }))
public class MealGroup extends BaseEntity {

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "combo_id")
	Combo combo;

	@NotNull
	String name;

	@NotNull
	Boolean optional;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = TableName.COMBO_GROUP_MEAL, 
			joinColumns = { @JoinColumn(name = "group_id") }, 
			inverseJoinColumns = { @JoinColumn(name = "meal_id") })
	List<Meal> meals;
}
