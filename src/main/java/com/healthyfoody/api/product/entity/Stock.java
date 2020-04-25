package com.healthyfoody.api.product.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.healthyfoody.api.location.Store;
import com.healthyfoody.api.common.TableName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@IdClass(StockId.class)
@Table(name = TableName.STOCK)
public class Stock {
	
	 @Id
	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "product_id")
	 Product product;
	 
	 @Id
	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "store_id")
	 Store store;
	 
	 @NotNull
	 Boolean inStock;
	 
	 LocalDateTime restockedOn; 
	 
}
