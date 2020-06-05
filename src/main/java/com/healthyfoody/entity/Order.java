package com.healthyfoody.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = TableName.ORDER)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
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
			price = price.add(op.getPrice().multiply(BigDecimal.valueOf(op.getQuantity())));
		}
		return price.setScale(2,RoundingMode.FLOOR);
	}
}
