package com.luciano.books.util;

public class StaticIds {
    
    private static int bookId = -1;
    private static int customerId = -1;
    private static int categoryId = -1;
    private static int orderId = -1;

    public static int getBookId() {
        bookId++;
        return bookId;
    }

    public static int getCustomerId() {
        customerId++;
        return customerId;
    }

    public static int getCategoryId() {
        categoryId++;
        return categoryId;
    }

    public static int getOrderId() {
        orderId++;
        return orderId;
    }
}
