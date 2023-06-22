package com.spiderweb.customer.exception;

public class InvalidNameException extends RuntimeException{
    public InvalidNameException(String message) {
        super(message);
    }
}
