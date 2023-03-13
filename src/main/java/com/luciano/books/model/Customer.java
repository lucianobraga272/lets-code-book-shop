package com.luciano.books.model;

import com.luciano.books.util.StaticIds;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Customer implements IModel {
    
    private Integer id;
    private String name;
    private String cpf;

    public Customer() {
        this.id = StaticIds.getCustomerId();
    }
    public Customer(String name, String cpf) {
        this.id = StaticIds.getCustomerId();
        this.name = name;
        this.cpf = cpf;
    }
    
    @Override
    public String toString() {
        return "Customer [id=" + id + ", name=" + name + ", cpf=" + cpf + "]";
    } 
}