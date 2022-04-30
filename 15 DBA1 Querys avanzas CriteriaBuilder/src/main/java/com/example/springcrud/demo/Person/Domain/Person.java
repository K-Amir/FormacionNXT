package com.example.springcrud.demo.Person.Domain;

import com.example.springcrud.demo.Professor.Domain.Professor;
import com.example.springcrud.demo.Student.Domain.Student;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;

import java.util.Date;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Integer id;

    String user;

    String password;

    String name;

    String surname;

    String company_email;

    String personal_email;

    String city;

    Boolean active;

    Date created_date = Timestamp.from(Instant.now());

    String image_url;

    Date termination_date;



}
