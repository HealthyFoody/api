package com.healthyfoody.api.orders;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.healthyfoody.api.customers.Customer;
import com.healthyfoody.api.location.Store;
import com.healthyfoody.api.common.BaseEntity;
import com.healthyfoody.api.common.TableName;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = TableName.ORDER)
public class Order extends BaseEntity {
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "store_id")
	private Store store;

	@NotNull
	private LocalDateTime orderDate;
	
	@NotNull
	private LocalDateTime programmedFor;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OrderDetail> orderDetails;
}
