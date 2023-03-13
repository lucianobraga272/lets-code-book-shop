package com.luciano.books.service;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.luciano.books.model.Book;
import com.luciano.books.model.Category;
import com.luciano.books.repository.BookRepository;

@Service
public class BookService extends GenericService<Book, BookRepository> {

    public BookService(BookRepository repository) {
        super(repository);
    }
    
    public Collection<Book> findByCategory(Category category){
        return repository.findByCategory(category);
    }
    public Book sell(Integer id) {
        return repository.sell(id);
    }
}
