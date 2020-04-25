package com.healthyfoody.api.product.controller.resource;

import java.util.List;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Relation(collectionRelation = "combos", itemRelation = "combo")
@JsonInclude(Include.NON_NULL)
public class GroupModel extends RepresentationModel<GroupModel> {

	String group;
	
	List<ProductModel> options;
}
