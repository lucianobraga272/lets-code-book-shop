package com.luciano.books.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class BookDTO {
    @NonNull
    private String name;
    @NonNull
    private String category;
    @NonNull
    private Double price;
    @NonNull
    private Integer units;

    @Override
    public String toString() {
        return "BookDTO [name=" + name + ", category=" + category + ", price=" + price + ", units=" + units + "]";
    }
}
