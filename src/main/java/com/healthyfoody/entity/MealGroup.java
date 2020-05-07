package com.healthyfoody.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = TableName.MEAL_GROUP, uniqueConstraints = @UniqueConstraint(columnNames = { "combo_id", "name" }))
public class MealGroup extends BaseEntity {

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "combo_id")
	Combo combo;

	@NotNull
	String name;

	@NotNull
	Boolean optional;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = TableName.COMBO_GROUP_MEAL, joinColumns = {
			@JoinColumn(name = "group_id") }, inverseJoinColumns = { @JoinColumn(name = "meal_id") })
	List<Meal> meals;
}
