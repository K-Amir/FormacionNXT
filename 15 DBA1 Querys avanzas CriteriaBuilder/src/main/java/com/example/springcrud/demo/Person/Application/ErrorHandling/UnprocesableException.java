package com.example.springcrud.demo.Person.Application.ErrorHandling;

public class UnprocesableException extends RuntimeException {
    public UnprocesableException(String message){
        super(message);
    }
}
