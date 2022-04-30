package com.example.springcrud.demo.Professor.Domain;

import com.example.springcrud.demo.Person.Domain.Person;
import com.example.springcrud.demo.Student.Domain.Student;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    @NotNull
    String comments;

    @NotNull
    String branch;


    @OneToOne
    @JoinColumn(name = "person_id")
    @JsonBackReference
    Person person;

    @OneToMany(
            mappedBy = "professor"
    )
    @JsonBackReference
    List<Student> students = new ArrayList<>();




}
