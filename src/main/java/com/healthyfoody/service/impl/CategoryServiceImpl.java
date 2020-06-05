package com.healthyfoody.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.healthyfoody.dto.response.CategoryResponse;
import com.healthyfoody.entity.Category;
import com.healthyfoody.exception.ResourceNotFoundException;
import com.healthyfoody.mapper.CategoryMapper;
import com.healthyfoody.repository.jpa.CategoryRepository;
import com.healthyfoody.service.CategoryService;
import com.healthyfoody.util.PageMapper;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;
    
    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public Page<CategoryResponse> findAll(int page, int size) {

        Page<Category> result = categoryRepository.findAll(PageRequest.of(page, size));
        return PageMapper.mapPage(result, categoryMapper);
    }

    @Override
    public List<CategoryResponse> findAll() {
    	List<Category> result = categoryRepository.findAll();
        return categoryMapper.mapResponseList(result);
    }

    @Override
    public CategoryResponse findById(UUID id) throws ResourceNotFoundException {
        Category result =  findEntityById(id);
        return categoryMapper.toResponse(result);
    }

	@Override
	public Category findEntityById(UUID id) throws ResourceNotFoundException {
		return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id, Category.class));
	}
}
