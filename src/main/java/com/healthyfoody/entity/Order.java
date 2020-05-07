package com.healthyfoody.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = TableName.ORDER)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Order extends BaseEntity {

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "store_id")
	private Store store;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OrderProduct> orderProducts;

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "courier_id")
	private Courier courier;

	@NotNull
	private String address;

	@NotNull
	private Double latitude;

	@NotNull
	private Double longitude;

	@Enumerated(EnumType.STRING)
	private OrderStatus status;

	@Enumerated(EnumType.STRING)
	private PaymentType paymentType;

	private String chargeId;

	@NotNull
	@OrderBy("DESC")
	private LocalDateTime orderDate;

	@NotNull
	private LocalDateTime programmedFor;

	@OneToMany(mappedBy = "order", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<Tracking> tracking;

	private String customerRequest;

	public BigDecimal getPrice() {

		BigDecimal price = new BigDecimal(0L);
		for (OrderProduct op: orderProducts) {
			price = price.add(op.getPrice());
		}
		return price.setScale(2,RoundingMode.FLOOR);
	}
}
