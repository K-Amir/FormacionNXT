package com.example.springcrud.demo.Student.Domain;

import com.example.springcrud.demo.Person.Domain.Person;
import com.example.springcrud.demo.Professor.Domain.Professor;
import com.example.springcrud.demo.Subject.Domain.Subject;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    @NotNull
    int numHoursWeek;

    String comments;

    String branch;

    @OneToOne
    @JoinColumn(name = "person_id")
    @JsonBackReference
    Person person;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonManagedReference
    Professor professor;

    @OneToMany
    @JoinColumn(name = "student_id")
    List<Subject> subjects = new ArrayList<>();



}
