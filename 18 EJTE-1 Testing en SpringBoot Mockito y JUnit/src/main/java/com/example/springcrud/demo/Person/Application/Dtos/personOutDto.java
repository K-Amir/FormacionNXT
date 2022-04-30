package com.example.springcrud.demo.Person.Application.Dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class personOutDto {
    Integer id;
    String user;
    String name;
    String password;
    String surname;
    String company_email;
    String personal_email;
    String city;
    Boolean active;
    Date created_date;
    String image_url;
    Date termination_date;
}
