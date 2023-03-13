package com.luciano.books.service;

import org.springframework.stereotype.Service;

import com.luciano.books.model.Book;
import com.luciano.books.repository.BookRepository;

@Service
public class BookService extends GenericService<Book, BookRepository> {

    public BookService(BookRepository repository) {
        super(repository);
    }

    public Book sell(Integer id) {
        return repository.sell(id);
    }
}
