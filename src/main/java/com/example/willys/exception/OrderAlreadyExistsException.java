package com.example.willys.exception;

public class OrderAlreadyExistsException extends RuntimeException{
    public OrderAlreadyExistsException(String customerId, String productId) {
        super("Order already exists for customer and product");
    }
}
