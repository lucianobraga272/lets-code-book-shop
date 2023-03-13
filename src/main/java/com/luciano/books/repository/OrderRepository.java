package com.luciano.books.repository;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import com.luciano.books.model.Order;

@Repository
public class OrderRepository extends GenericRepository<Order> {

    public OrderRepository(Collection<Order> db) {
        super(db);
    }
}
