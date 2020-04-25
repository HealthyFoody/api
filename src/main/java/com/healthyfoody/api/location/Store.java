package com.healthyfoody.api.location;

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
@Table(name = TableName.STORE)
public class Store extends BaseEntity {

	@NotNull
	String description;
	
	@NotNull
	Double latitude;
	
	@NotNull
	Double longitude;
}
