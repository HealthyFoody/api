package com.healthyfoody.api.product.controller.resource;

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


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Relation(collectionRelation = "categories", itemRelation = "category")
@JsonInclude(Include.NON_NULL)
public class CategoryModel extends RepresentationModel<CategoryModel> {
	UUID id;

	String name;

	String description;
}
