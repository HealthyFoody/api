package com.healthyfoody.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = TableName.COURIER)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Courier extends BaseEntity {

	@NotNull
	String firstName;

	@NotNull
	String lastName;

	@NotNull
	Boolean active;
}
