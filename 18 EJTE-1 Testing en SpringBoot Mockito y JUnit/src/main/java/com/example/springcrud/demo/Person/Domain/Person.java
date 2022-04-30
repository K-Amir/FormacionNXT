package com.example.springcrud.demo.Person.Domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
