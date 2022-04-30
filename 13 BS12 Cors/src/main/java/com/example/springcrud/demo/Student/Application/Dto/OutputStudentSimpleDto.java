package com.example.springcrud.demo.Student.Application.Dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OutputStudentSimpleDto {
    String id;

    String branch;

    String comments;

    int numHoursWeek;

}
