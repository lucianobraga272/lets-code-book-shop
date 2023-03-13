package com.luciano.books.model;

import com.luciano.books.util.StaticIds;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Category implements IModel {
    
    private Integer id;
    private String name;

    public Category() {
        this.id = StaticIds.getCategoryId();
    }

    public Category(String name) {
        this.id = StaticIds.getCategoryId();
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category [id=" + id + ", name=" + name + "]";
    }
}