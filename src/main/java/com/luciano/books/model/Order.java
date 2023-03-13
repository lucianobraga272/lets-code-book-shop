package com.luciano.books.model;

import java.time.LocalDate;
import java.util.List;

import com.luciano.books.util.StaticIds;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Order implements IModel {
    private Integer id;
    private List<Book> books;
    private Customer customer;
    private LocalDate dateOrder;
    private double price;

    public Order() {
        this.id = StaticIds.getOrderId();
    }

    public Order(List<Book> books, Customer customer, LocalDate dateOrder, double price) {
        this.id = StaticIds.getOrderId();
        this.books = books;
        this.customer = customer;
        this.dateOrder = dateOrder;
        this.price = price;
    }

    public String getName() {
        return customer.getName();
    }

    @Override
    public String toString() {
        return "Order [id=" + id + ", books=" + books + ", customer=" + customer + ", dateOrder=" + dateOrder
                + ", price=" + price + "]";
    }
}