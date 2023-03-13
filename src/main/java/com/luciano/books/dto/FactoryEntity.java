package com.luciano.books.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.luciano.books.exception.NotFoundException;
import com.luciano.books.model.Book;
import com.luciano.books.model.Category;
import com.luciano.books.model.Order;
import com.luciano.books.service.BookService;
import com.luciano.books.service.CustomerService;
import com.luciano.books.util.GetBooksTotalPrice;

import lombok.AllArgsConstructor;

import com.luciano.books.model.Customer;

@Service
@AllArgsConstructor
public class FactoryEntity {
    CustomerService customerService;
    BookService bookService;

    public Category dtoToEntity(CategoryDTO categoryDTO) {
        return new Category(categoryDTO.getName());
    }

    public Book dtoToEntity(BookDTO bookDTO) {
        Book book = new Book();
        book.setName(bookDTO.getName());
        book.setCategory(new Category(bookDTO.getCategory()));
        book.setPrice(bookDTO.getPrice());
        book.setUnits(bookDTO.getUnits());
        return book;
    }

    public Customer dtoToEntity(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setCpf(customerDTO.getCpf());
        customer.setName(customerDTO.getName());

        return customer;
    }

    public Order dtoToEntity(OrderDTO orderDTO) {
        Order order = new Order();
        
        order.setDateOrder(LocalDate.parse(orderDTO.getDateOrder(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        order.setPrice(orderDTO.getPrice());
        
        var customer = customerService.getByName(orderDTO.getCustomerName());
        if(!customer.isPresent())
            throw new NotFoundException("Livro não encontrado!");
        order.setCustomer(customer.get());
        
        var list = orderDTO.getBooksName();
        var books = new ArrayList<Book>();
        for (int i = 0; i < list.size(); i++) {
            var book =bookService.getByName(list.get(i)); 
            if(!book.isPresent())
                throw new NotFoundException("Livro não encontrado!");
            books.add(book.get());
        }
        order.setBooks(books);
        
        order.setPrice(GetBooksTotalPrice.booksTotalPrice(order));

        return order;
    }
}
