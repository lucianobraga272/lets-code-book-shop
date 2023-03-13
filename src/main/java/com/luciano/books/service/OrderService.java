package com.luciano.books.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luciano.books.model.Book;
import com.luciano.books.model.Order;
import com.luciano.books.repository.OrderRepository;

import lombok.AllArgsConstructor;

@Service
public class OrderService extends GenericService<Order, OrderRepository> {
    
    @Autowired
    private BookService bookService;

    public OrderService(OrderRepository repository) {
        super(repository);
        //TODO Auto-generated constructor stub
    }

    @Override
    public Order save(Order t) {
        for (Book book : t.getBooks()) {
            bookService.sell(book.getId());
        }
        return super.save(t);
    }

    
}
