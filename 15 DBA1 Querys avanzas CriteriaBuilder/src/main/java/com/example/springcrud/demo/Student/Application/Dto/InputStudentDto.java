package com.example.springcrud.demo.Student.Application.Dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@Data
@NoArgsConstructor
@AllArgsConstructor
public class InputStudentDto {

    String branch;

    String comments;

    int numHoursWeek;

    int person_id;

    int professor_id;
}
