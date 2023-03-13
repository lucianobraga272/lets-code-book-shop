package com.luciano.books.repository;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.luciano.books.model.Book;

@Repository
public class BookRepository extends GenericRepository<Book> {

    public BookRepository(Collection<Book> db) {
        super(db);
    }

    public Book sell(Integer id) {
        var list = db.stream().collect(Collectors.toList());
        Book book = new Book();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equals(id)) {
                book = list.get(i);
            }
        }
        if (book.getUnits() < 1) {
            return new Book();
        }
        book.setUnits(book.getUnits() - 1);

        db = Collections.synchronizedCollection(list);

        return book;
    }

}
