package com.spiderweb.customer.advice;

import com.spiderweb.customer.exception.CustomErrorResponse;
import com.spiderweb.customer.exception.EmailTakenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZonedDateTime;

@RestControllerAdvice
public class CustomerErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<CustomErrorResponse> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception){
        CustomErrorResponse errorResponse = new CustomErrorResponse(
                exception.getBindingResult().getFieldError().getDefaultMessage(),
                ZonedDateTime.now(),
                org.apache.http.HttpStatus.SC_BAD_REQUEST

        );

        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailTakenException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<CustomErrorResponse> handleEmailTakenException(
            EmailTakenException exception){
        CustomErrorResponse errorResponse = new CustomErrorResponse(
                exception.getMessage(),
                ZonedDateTime.now(),
                400
        );

//        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
        return ResponseEntity.badRequest().body(errorResponse);

    }

}
