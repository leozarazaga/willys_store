package com.example.willys.exception;

public class CustomerNameNotFoundException extends RuntimeException{
    public CustomerNameNotFoundException(String name) {
        super("Customer with name '" + name + "' does not exist in our database.");
    }
}
