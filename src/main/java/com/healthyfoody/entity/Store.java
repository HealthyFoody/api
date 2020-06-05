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
@Table(name = TableName.STORE)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Store extends BaseEntity {

	@NotNull
	String description;

	@NotNull
	Double latitude;

	@NotNull
	Double longitude;
}
