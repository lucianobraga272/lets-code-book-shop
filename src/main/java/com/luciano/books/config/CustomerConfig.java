package com.luciano.books.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.luciano.books.model.Customer;

@Configuration
public class CustomerConfig {

    @Bean
    public Collection<Customer> getCustomers() {
        List<Customer> customers = new ArrayList<>();

        Customer customer1 = new Customer();
        customer1.setName("Ronaldo Gaucho");
        customer1.setCpf("123.456.789-10");
        customers.add(customer1);
        Customer customer2 = new Customer();
        customer2.setName("Diego Tardelli");
        customer2.setCpf("321.654.987-01");
        customers.add(customer2);
        Customer customer3 = new Customer();
        customer3.setName("Lionel Messi");
        customer3.setCpf("987.456.321-10");
        customers.add(customer3);

        return Collections.synchronizedCollection(customers);
    }
}