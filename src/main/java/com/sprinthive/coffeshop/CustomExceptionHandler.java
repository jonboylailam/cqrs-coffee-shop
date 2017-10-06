package com.sprinthive.coffeshop;

import com.sprinthive.coffeshop.customer.CustomerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseMsg CustomerNotFoundException(CustomerNotFoundException ex) {
        return ResponseMsg.builder().msg(ex.getMessage()).build();
    }
}
