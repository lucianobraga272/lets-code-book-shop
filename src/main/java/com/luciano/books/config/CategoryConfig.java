package com.luciano.books.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.luciano.books.model.Category;

@Configuration
public class CategoryConfig {

    @Bean
    public Collection<Category> getCategories() {
        List<Category> categories = new ArrayList<>();
        Category category1 = new Category();
        category1.setId(0);
        category1.setName("Ficção");
        categories.add(category1);
        Category category2 = new Category();
        category2.setId(1);
        category2.setName("Fantasia");
        categories.add(category2);
        Category category3 = new Category();
        category3.setId(2);
        category3.setName("Aventura");
        categories.add(category3);
        return Collections.synchronizedCollection(categories);
    }

}
