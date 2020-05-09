package com.healthyfoody.entity;

import com.fasterxml.jackson.annotation.*;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = TableName.PRODUCT)
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(
		use = JsonTypeInfo.Id.NAME,
		property = "type")
@JsonSubTypes({
		@JsonSubTypes.Type(value = Meal.class, name = "meal"),
		@JsonSubTypes.Type(value = Combo.class, name = "combo")
})
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
