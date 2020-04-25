package com.healthyfoody.api.orders;

import java.math.BigDecimal;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.healthyfoody.api.common.BaseEntity;
import com.healthyfoody.api.product.entity.Combo;
import com.healthyfoody.api.product.entity.Product;
import com.healthyfoody.api.common.TableName;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = TableName.ORDER_DETAIL, uniqueConstraints = {@UniqueConstraint(columnNames = {"product_id", "combo_id"})})
public class OrderDetail extends BaseEntity {

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Order order;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Product product;

	@NotNull
	@Column(scale = 2, precision = 5)
	private BigDecimal price;
	
	@NotNull
	private Integer quantity;

	@NotNull
	private Integer bundledQuantity;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Combo combo;

	private String customerRequest;
}
