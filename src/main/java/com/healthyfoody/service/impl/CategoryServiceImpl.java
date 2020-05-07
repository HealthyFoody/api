package com.healthyfoody.service.impl;

import com.healthyfoody.entity.Category;
import com.healthyfoody.exception.ResourceNotFoundException;
import com.healthyfoody.repository.jpa.CategoryRepository;
import com.healthyfoody.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Page<Category> findAll(int page, int size) {

        return categoryRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(UUID id) throws ResourceNotFoundException {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id, Category.class));
    }
}
