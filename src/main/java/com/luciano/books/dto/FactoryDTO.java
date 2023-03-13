package com.luciano.books.dto;

import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

import com.luciano.books.model.Book;
import com.luciano.books.model.Category;
import com.luciano.books.model.Order;
import com.luciano.books.util.GetBooksTotalPrice;
import com.luciano.books.model.Customer;

public class FactoryDTO {

    public static CategoryDTO entityToDTO(Category category) {
        return new CategoryDTO(category.getName());
    }

    public static BookDTO entityToDTO(Book book) {
        return new BookDTO(book.getName(), book.getCategory().getName(), book.getPrice(), book.getUnits());
    }

    public static CustomerDTO entityToDTO(Customer customer) {
        return new CustomerDTO(customer.getName(), customer.getCpf());
    }

    public static OrderDTO entityToDTO(Order order) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return new OrderDTO(order.getName(),
                order.getBooks().stream().map(b -> b.getName()).collect(Collectors.toList()),
                dateTimeFormatter.format(order.getDateOrder()),
                GetBooksTotalPrice.booksTotalPrice(order));
    }
}
