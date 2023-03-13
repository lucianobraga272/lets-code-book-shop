package com.luciano.books.service;

import org.springframework.stereotype.Service;

import com.luciano.books.model.Category;
import com.luciano.books.repository.CategoryRepository;

@Service
public class CategoryService extends GenericService<Category, CategoryRepository> {

    public CategoryService(CategoryRepository repository) {
        super(repository);
    }
}
