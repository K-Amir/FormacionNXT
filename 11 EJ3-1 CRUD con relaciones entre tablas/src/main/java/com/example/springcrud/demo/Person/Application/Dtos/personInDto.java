package com.example.springcrud.demo.Person.Application.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class personInDto {

    String user;

    String password;

    String name;

    String surname;

    String company_email;

    String personal_email;

    String city;

    Boolean active;

    Date created_date;

    String image_url;

    Date termination_date;
}
