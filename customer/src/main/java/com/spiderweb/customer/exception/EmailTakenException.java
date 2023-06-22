package com.spiderweb.customer.exception;

public class EmailTakenException extends RuntimeException{
    public EmailTakenException(String message) {
        super(message);
    }
}
