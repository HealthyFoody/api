package com.healthyfoody.api.product.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.healthyfoody.api.common.BaseEntity;
import com.healthyfoody.api.common.TableName;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = TableName.CATEGORY)
public class Category extends BaseEntity {

	@NotNull
	@Column(unique = true)
	String name;

	String description;

	@JsonIgnore
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST })
	@JoinTable(name = TableName.CATEGORY_PRODUCT, 
			joinColumns = { @JoinColumn(name = "category_id") }, 
			inverseJoinColumns = { @JoinColumn(name = "product_id") } )
	List<Product> products;

	String imageUrl;
}
