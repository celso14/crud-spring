package com.celso.crudspring.controllers;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.celso.crudspring.exceptions.NotFoundException;

import org.springframework.http.HttpStatus;

@RestControllerAdvice
public class ApplicationControllerAdvice {
    
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFoundException(Exception ex) {
        return "Error: " + ex.getMessage();
    }
}
