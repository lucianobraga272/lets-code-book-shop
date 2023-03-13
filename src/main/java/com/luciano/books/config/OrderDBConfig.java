package com.luciano.books.config;

import java.time.LocalDate;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Configuration;

import com.luciano.books.model.Order;
import com.luciano.books.repository.OrderRepository;
import com.luciano.books.service.BookService;
import com.luciano.books.service.CustomerService;
import com.luciano.books.util.GetBooksTotalPrice;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class OrderDBConfig {

    private OrderRepository orderRepository;
    private CustomerService customerService;
    private BookService bookService;

    @PostConstruct
    public void initDB() {
        Order order1 = new Order();
        order1.setBooks(List.of(
                bookService.getByName("Game of Thrones").get(),
                bookService.getByName("As Cronicas de Narnia").get()));

        order1.setDateOrder(LocalDate.now());
        order1.setCustomer(customerService.getByCpf("123.456.789-10").get());
        double totalPrice = GetBooksTotalPrice.booksTotalPrice(order1);
        order1.setPrice(totalPrice);
        orderRepository.create(order1);

    }
}
