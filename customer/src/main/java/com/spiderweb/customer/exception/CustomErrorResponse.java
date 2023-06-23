package com.spiderweb.customer.exception;

import java.time.ZonedDateTime;

public record CustomErrorResponse(String message, ZonedDateTime timeStamp, int status) {

}
