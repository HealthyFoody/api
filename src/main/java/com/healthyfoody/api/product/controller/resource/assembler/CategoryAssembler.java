package com.healthyfoody.api.product.controller.resource.assembler;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.CollectionModel;

import com.healthyfoody.api.product.controller.ProductController;
import com.healthyfoody.api.product.entity.Category;
import com.healthyfoody.api.product.controller.resource.CategoryModel;
import org.springframework.stereotype.Component;

@Component
public class CategoryAssembler extends RepresentationModelAssemblerSupport<Category, CategoryModel>{

	public CategoryAssembler() {
		super(ProductController.class, CategoryModel.class);
	}

	@Override
	public CategoryModel toModel(Category entity) {
		
		CategoryModel model = instantiateModel(entity);
		
		model.add(linkTo(
				methodOn(ProductController.class)
				.getCategoryById(entity.getId()))
				.withSelfRel());

		model.setId(entity.getId());
		model.setName(entity.getName());
		model.setDescription(entity.getDescription());

		model.add(linkTo(methodOn(ProductController.class).getProductsByCategory(entity.getId(),null)).withRel("products"));
		
		
		return model;
	}

	@Override
	public CollectionModel<CategoryModel> toCollectionModel(Iterable<? extends Category> entities) {
		// TODO Auto-generated method stub
		CollectionModel<CategoryModel> models =  super.toCollectionModel(entities);
		
		models.add(linkTo(methodOn(ProductController.class).getAllCategories()).withSelfRel());
		return models;
	}
	
	
	
}
