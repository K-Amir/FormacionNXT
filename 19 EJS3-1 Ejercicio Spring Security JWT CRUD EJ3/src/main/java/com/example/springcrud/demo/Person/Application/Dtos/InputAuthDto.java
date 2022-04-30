package com.example.springcrud.demo.Person.Application.Dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InputAuthDto {
    private String email;

    private String password;
}
