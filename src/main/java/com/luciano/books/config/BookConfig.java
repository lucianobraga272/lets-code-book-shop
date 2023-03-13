package com.luciano.books.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.luciano.books.model.Book;
import com.luciano.books.service.CategoryService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Configuration
public class BookConfig {

    CategoryService categoryService;

    @Bean
    public Collection<Book> getBooks() {
        List<Book> books = new ArrayList<>();
        Book book1 = new Book();
        book1.setName("Senhor dos Anéis");
        book1.setCategory(categoryService.get(1).get());
        book1.setPrice(50.99);
        book1.setUnits(100);
        books.add(book1);
        Book book2 = new Book();
        book2.setName("Game of Thrones");
        book2.setCategory(categoryService.get(1).get());
        book2.setPrice(39.99);
        book2.setUnits(200);
        books.add(book2);
        Book book3 = new Book();
        book3.setName("As Cronicas de Narnia");
        book3.setCategory(categoryService.get(1).get());
        book3.setPrice(29.99);
        book3.setUnits(50);

        books.add(book3);

        return Collections.synchronizedCollection(books);
    }
}
