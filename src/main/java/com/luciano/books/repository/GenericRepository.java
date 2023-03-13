package com.luciano.books.repository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.luciano.books.model.IModel;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Repository
public abstract class GenericRepository<T extends IModel> implements IRepository<T> {

    Collection<T> db;

    public List<T> getAll() {
        System.out.println(this.db.stream().collect(Collectors.toList()));
        return this.db.stream().collect(Collectors.toList());
    }

    public Optional<T> getByName(String name) {
        Optional<T> optional = Optional.empty();
        List<T> list = db.stream().collect(Collectors.toList());
        for (int i = 0; i < db.size(); i++) {
            if (list.get(i).getName().toLowerCase().equals(name.toLowerCase())) {
                optional = Optional.of(list.get(i));
                break;
            }
        }
        return optional;
    }

    public Optional<T> read(Integer id) {
        Optional<T> optional = Optional.empty();
        List<T> list = db.stream().collect(Collectors.toList());
        for (int i = 0; i < db.size(); i++) {
            if (list.get(i).getId() == id)
                optional = Optional.of(list.get(i));
        }
        return optional;
    }

    public T create(T t) {
        // t.setId(staticId++);
        db.add(t);
        System.out.println(t.getId());
        return t;
    }

    public T update(Integer id, T t) {
        List<T> list = db.stream().collect(Collectors.toList());
        t.setId(read(id).get().getId());
        list.set(id, t);
        db = Collections.synchronizedCollection(list);
        return t;
    }

    public boolean delete(Integer id) {
        List<T> list = db.stream().collect(Collectors.toList());
        try {
            list.remove(this.read(id).get());
            db = Collections.synchronizedCollection(list);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
