package com.spiderweb.customer.exception;


import org.apache.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler({EmailTakenException.class,
                        InvalidEmailException.class,
                        InvalidEmailException.class})
    public ResponseEntity<Object> handleCustomExceptions(RuntimeException exception){
        int badRequest = HttpStatus.SC_BAD_REQUEST;
        CustomErrorResponse errorResponse =
                new CustomErrorResponse(
                        exception.getMessage(),
                        ZonedDateTime.now(),
                        badRequest
                );

        return new ResponseEntity<>(errorResponse, org.springframework.http.HttpStatus.valueOf(badRequest));
    }
}
