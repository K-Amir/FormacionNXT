package com.example.springcrud.demo.Subject.Domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String subject;

    String comments;


    Date initial_date;

    Date finish_date;




}
