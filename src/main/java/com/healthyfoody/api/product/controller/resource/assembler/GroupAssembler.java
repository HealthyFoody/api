package com.healthyfoody.api.product.controller.resource.assembler;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.healthyfoody.api.product.controller.ProductController;
import com.healthyfoody.api.product.entity.Group;
import com.healthyfoody.api.product.entity.Meal;
import com.healthyfoody.api.product.controller.resource.GroupModel;
import com.healthyfoody.api.product.controller.resource.ProductModel;
import org.springframework.stereotype.Component;

@Component
public class GroupAssembler extends RepresentationModelAssemblerSupport<Group, GroupModel> {

	public GroupAssembler() {
		super(ProductController.class, GroupModel.class);
	}

	@Override
	public GroupModel toModel(Group entity) {
		
		GroupModel model = instantiateModel(entity);
		
		model.setGroup(entity.getName());
		
		List<ProductModel> options = toProductModels(entity.getMeals());
		
		model.setOptions(options);
		
		return model;
	}
	
	private List<ProductModel> toProductModels(List<Meal> meals) {
		if (meals.isEmpty())
			return Collections.emptyList();
		
		return meals.stream().map(meal -> ProductModel.builder()
				.id(meal.getId())
				.name(meal.getName())
				.description(meal.getDescription())
				.price(meal.getPrice())
				.ingredients(meal.getIngredients())
				.cal(meal.getCalories().toString())
				.build()
				.add(linkTo(methodOn(ProductController.class)
						.getProductById(meal.getId()))
						.withSelfRel()))
				.collect(Collectors.toList());

	}
}

