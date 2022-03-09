package com.example.demo1.runner;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandling extends ResponseEntityExceptionHandler{
    @ExceptionHandler
    public ResponseEntity<RouteNotFound> handleExceptions( RouteNotFoundException exception) {
    	RouteNotFound error = new RouteNotFound();
    	error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exception.getMessage());
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }
}
