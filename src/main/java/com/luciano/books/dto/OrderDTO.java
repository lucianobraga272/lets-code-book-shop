package com.luciano.books.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDTO {
    
    @NonNull
    private String customerName;
    @NonNull
    private List<String> booksName;
    @NonNull
    private String dateOrder;
    @NonNull
    private Double price;

    @Override
    public String toString() {
        return "OrderDTO [customerName=" + customerName + ", booksName=" + booksName + ", dateOrder=" + dateOrder
                + ", price=" + price + "]";
    }
}
