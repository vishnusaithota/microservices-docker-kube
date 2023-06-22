package com.spiderweb.customer.exception;

import java.time.ZonedDateTime;

public class CustomErrorResponse {

    private final String message;
    private final ZonedDateTime timeStamp;
    private final int status;

    public CustomErrorResponse(String message, ZonedDateTime timeStamp, int status) {
        this.message = message;
        this.timeStamp = timeStamp;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public ZonedDateTime getTimeStamp() {
        return timeStamp;
    }

    public int getStatus() {
        return status;
    }
}
