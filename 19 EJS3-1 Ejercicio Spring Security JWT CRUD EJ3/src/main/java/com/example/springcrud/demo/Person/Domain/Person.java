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
    private Integer id;

    private String user;

    private String password;

    private String name;

    private String surname;

    private String company_email;

    private String personal_email;

    private String city;

    private Boolean active;

    private Date created_date = Timestamp.from(Instant.now());

    private String image_url;

    private Date termination_date;

    private boolean isAdmin;



}
