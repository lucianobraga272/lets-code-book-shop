package com.luciano.books.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.luciano.books.model.Customer;
import com.luciano.books.repository.CustomerRepository;

@Service
public class CustomerService extends GenericService<Customer, CustomerRepository> {

    public CustomerService(CustomerRepository repository) {
        super(repository);
    }

    public Optional<Customer> getByCpf(String cpf) {
        return repository.getByCpf(cpf);
    }
}
