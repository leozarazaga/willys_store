package com.example.willys.exception;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(String customerId, String productId) {
        super("The order with customer id: '" + customerId + "' and product id: '" + productId + "' does not exist in our database.");
    }
}
