package com.example.springcrud.demo.Person.Application.ErrorHandling;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message){
        super(message);
    }
}
