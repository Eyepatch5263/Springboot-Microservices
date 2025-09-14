package com.eyepatch.works.orderservice.domain;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(String message){
        super(message);
    }
    public static OrderNotFoundException orderNotFoundException(String message){
        return new OrderNotFoundException("Order with Number "+ message + " not found");
    }
}
