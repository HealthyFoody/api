package com.healthyfoody.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@Table(name = TableName.PRODUCT)
@Inheritance(strategy = InheritanceType.JOINED)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public abstract class Product extends BaseEntity {

	@NotNull
	String name;

	@NotNull
	String description;

	@JsonIgnore
	@ManyToMany(mappedBy = "products")
	List<Category> categories;

	@NotNull
	@Column(precision = 5, scale = 2)
	BigDecimal price;

	@NotNull
	Boolean listed;

	@ManyToOne(fetch = FetchType.EAGER, optional = true)
	@JoinColumn(name = "sale_time_span_id")
	SaleTimeSpan saleTimeSpan;

	String imageUrl;
}
