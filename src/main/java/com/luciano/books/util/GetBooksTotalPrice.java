package com.luciano.books.util;

import com.luciano.books.model.Order;

public class GetBooksTotalPrice {
    public static double booksTotalPrice(Order order1) {
        return order1.getBooks()
                .stream()
                .map((b) -> b.getPrice())
                .mapToDouble(d -> d.doubleValue())
                .sum();
    }
}
