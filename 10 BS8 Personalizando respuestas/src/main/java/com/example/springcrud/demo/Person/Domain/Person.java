package com.example.springcrud.demo.Person.Domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Entity
@Data
public class Person {
    @Id
    @GeneratedValue
    Integer id;

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
