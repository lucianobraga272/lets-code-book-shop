package com.luciano.books.model;

import com.luciano.books.util.StaticIds;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Book implements IModel {
    
    private Integer id;
    private String name;
    private Category category;
    private Double price;
    private Integer units;

    public Book() {
        this.id = StaticIds.getBookId();
    }

    public Book(String name, Category category, Double price, Integer units) {
        this.id = StaticIds.getBookId();
        this.name = name;
        this.category = category;
        this.price = price;
        this.units = units;
    }

    @Override
    public String toString() {
        return name + " - preço: " + price;
    }
}