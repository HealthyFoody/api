package com.healthyfoody.api.product.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.healthyfoody.api.common.BaseEntity;
import com.healthyfoody.api.common.TableName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = TableName.PRODUCT)
public abstract class Product extends BaseEntity {
	
	@NotNull
	String name;
	
	@NotNull
	String description;

	@ManyToMany(mappedBy = "products")
	List<Category> categories;
	
	@NotNull
	@Column(precision = 5, scale = 2)
	BigDecimal price;
	
	@NotNull
	Boolean discontinued;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = true)
	@JoinColumn(name = "sale_time_span_id")
    SaleTimeSpan saleTimeSpan;

	String imageUrl;
}
