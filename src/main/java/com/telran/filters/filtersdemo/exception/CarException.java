package com.telran.filters.filtersdemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CarException extends RuntimeException{

    public CarException() {
    }

    public CarException(String message) {
        super(message);
    }
}
