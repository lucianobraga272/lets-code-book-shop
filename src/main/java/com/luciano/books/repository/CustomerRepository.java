package com.luciano.books.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.luciano.books.model.Customer;

@Repository
public class CustomerRepository extends GenericRepository<Customer> {

    public CustomerRepository(Collection<Customer> db) {
        super(db);
    }

    public Optional<Customer> getByCpf(String cpf) {
        Optional<Customer> optional = Optional.empty();
        List<Customer> list = db.stream().collect(Collectors.toList());
        for (int i = 0; i < db.size(); i++) {
            if (list.get(i).getCpf().equals(cpf)) {
                optional = Optional.of(list.get(i));
                break;
            }
        }
        return optional;
    }
}
