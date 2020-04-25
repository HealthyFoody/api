package com.healthyfoody.api.product.controller.resource;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import com.healthyfoody.api.common.ValidUUID;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Relation(collectionRelation = "products", itemRelation = "product")
@JsonInclude(Include.NON_NULL)
public class ProductModel extends RepresentationModel<ProductModel> {

	@ValidUUID
	UUID id;

	String name;

	List<CategoryModel> categories;

	String description;

	@NotNull
	@Positive
	BigDecimal price;

	Boolean discontinued;
	
	String saleStartsAt;
	
	String saleStopsAt;

	@NotNull
	String type;

	/* MEALS */
	//FIXME: transform to string list
	String ingredients;
	
	String cal;

	/* COMBOS */
	String fromDate;
	String toDate;
	
	List<GroupModel> groups;

	String imageUrl;
}
