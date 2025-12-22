package com.example.demo.service.impl;

import com.example.demo.exception.ConflictException;
import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repo;

    public CategoryServiceImpl(CategoryRepository repo) {
        this.repo = repo;
    }

    @Override
    public Category addCategory(Category category) {
        if (repo.existsByName(category.getName())) {
            throw new ConflictException("Category already exists");
        }
        return repo.save(category);
    }

    @Override
    public List<Category> getAllCategories() {
        return repo.findAll();
    }
}
