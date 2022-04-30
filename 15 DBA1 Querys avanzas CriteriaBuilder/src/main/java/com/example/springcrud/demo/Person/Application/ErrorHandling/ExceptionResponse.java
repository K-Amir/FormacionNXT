package com.example.springcrud.demo.Person.Application.ErrorHandling;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ExceptionResponse {
    private Date timestamp;
    private int HttpCode;
}
