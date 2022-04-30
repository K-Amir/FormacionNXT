package com.example.springcrud.demo.Subject.Application.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
public class OutputStudentSubjectDto {
    String id;
    String subject;
    Date initial_date;
    Date finish_date;

}
