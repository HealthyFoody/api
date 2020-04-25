package com.healthyfoody.api.product.entity;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.healthyfoody.api.common.BaseEntity;
import com.healthyfoody.api.common.TableName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = TableName.SALE_TIME_SPAN, uniqueConstraints = { @UniqueConstraint(columnNames = {"starting_hour","ending_hour"})})
public class SaleTimeSpan extends BaseEntity {

	@NotNull
	String description;

	@Column(name = "starting_hour")
	LocalTime startingHour;

	@Column(name = "ending_hour")
	LocalTime endingHour;
}
