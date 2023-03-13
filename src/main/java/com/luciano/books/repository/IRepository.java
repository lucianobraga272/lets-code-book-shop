package com.luciano.books.repository;

import java.util.List;
import java.util.Optional;

import com.luciano.books.model.IModel;

public interface IRepository<T extends IModel> {
    T create(T t);

    T update(Integer id, T t);

    boolean delete(Integer i);

    Optional<T> read(Integer i);

    List<T> getAll();
}
