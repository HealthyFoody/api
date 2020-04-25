package com.healthyfoody.api.product.controller.resource.assembler;

import com.healthyfoody.api.common.ConverterUtil;
import com.healthyfoody.api.product.controller.resource.CategoryModel;
import com.healthyfoody.api.product.entity.Category;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.healthyfoody.api.product.controller.ProductController;
import com.healthyfoody.api.product.entity.Combo;
import com.healthyfoody.api.product.entity.Meal;
import com.healthyfoody.api.product.entity.Product;
import com.healthyfoody.api.product.controller.resource.ProductModel;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductAssembler extends RepresentationModelAssemblerSupport<Product, ProductModel> {

	public ProductAssembler() {
		super(ProductController.class, ProductModel.class);
	}

	@Override
	public ProductModel toModel(Product entity) {

		ProductModel model = instantiateModel(entity);
		
		model.add(linkTo(methodOn(ProductController.class).getProductById(entity.getId())).withSelfRel());
		
		model.setId(entity.getId());
		model.setName(entity.getName());
		model.setDescription(entity.getDescription());
		model.setDiscontinued(entity.getDiscontinued());
		model.setPrice(entity.getPrice());
		model.setImageUrl(entity.getImageUrl());

		model.setCategories(toCategoryModel(entity.getCategories()));
		
		//FIXME: validate for not null: saleTimeframe object and starting/ending hour fields
		//TODO: review if hour format is appropriate

		if (entity.getSaleTimeSpan() != null) {
			model.setSaleStartsAt(ConverterUtil.toString(entity.getSaleTimeSpan().getStartingHour()));
			model.setSaleStopsAt(ConverterUtil.toString(entity.getSaleTimeSpan().getEndingHour()));
		}
		

		if (entity instanceof Meal) {
			Meal meal = (Meal) entity;
			model.setType("meal");
			model.setCal(meal.getCalories().toString());
			model.setIngredients(meal.getIngredients());
		} 
		if (entity instanceof Combo) {
			Combo combo = (Combo) entity;
			model.setType("combo");
			model.setFromDate(combo.getFromDate().toString());
			model.setToDate(combo.getToDate().toString());
		}
		
		return model;
	}

	@Override
	public CollectionModel<ProductModel> toCollectionModel(Iterable<? extends Product> entities) {
		// TODO Auto-generated method stub
		return super.toCollectionModel(entities);
	}

	private List<CategoryModel> toCategoryModel(List<Category> categories) {
		if (categories.isEmpty())
			return Collections.emptyList();

		return categories.stream().map(cat -> CategoryModel
				.builder()
				.id(cat.getId())
				.name(cat.getName())
				.build()
				.add(linkTo(methodOn(ProductController.class).getCategoryById(cat.getId())).withSelfRel())
		).collect(Collectors.toList());
	}
	
	
}
