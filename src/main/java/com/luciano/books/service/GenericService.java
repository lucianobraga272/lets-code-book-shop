package com.luciano.books.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.luciano.books.model.IModel;
import com.luciano.books.repository.GenericRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public abstract class GenericService<T extends IModel, RE extends GenericRepository<T>> {

    RE repository;

    public Optional<List<T>> getAll() {
        return Optional.of(repository.getAll());
    }

    public Optional<T> get(Integer id) {
        return repository.read(id);
    }

    public Optional<T> getByName(String name) {
        return repository.getByName(name);
    }

    public T save(T t) {
        return repository.create(t);
    }

    public T update(Integer id, T t) {
        return repository.update(id, t);
    }

    public boolean delete(Integer id) {
        return repository.delete(id);
    }
}
