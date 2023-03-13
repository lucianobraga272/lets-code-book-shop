package com.luciano.books.repository;

import java.util.Collection;
import org.springframework.stereotype.Repository;

import com.luciano.books.model.Category;

@Repository
public class CategoryRepository extends GenericRepository<Category> {

    public CategoryRepository(Collection<Category> db) {
        super(db);
    }   
}