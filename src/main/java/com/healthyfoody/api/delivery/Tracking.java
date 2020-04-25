package com.healthyfoody.api.delivery;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.healthyfoody.api.orders.Order;
import com.healthyfoody.api.common.BaseEntity;
import com.healthyfoody.api.common.TableName;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = TableName.TRACKING)
public class Tracking extends BaseEntity {
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "order_id")
	Order order;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "courier_id")
	Courier courier;
	
	@NotNull
	Integer statusCode;
}
