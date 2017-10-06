package com.sprinthive.coffeshop.heath;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
public class HeathCheckException extends RuntimeException {

    public HeathCheckException(String message) {
        super(message);
    }
}
