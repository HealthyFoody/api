package com.healthyfoody.api.delivery;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.healthyfoody.api.common.BaseEntity;
import com.healthyfoody.api.common.TableName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = TableName.COURIER)
public class Courier extends BaseEntity {

	@NotNull
	String firstName;
	
	@NotNull
	String lastName;
	
	@NotNull
	Boolean active;
}
