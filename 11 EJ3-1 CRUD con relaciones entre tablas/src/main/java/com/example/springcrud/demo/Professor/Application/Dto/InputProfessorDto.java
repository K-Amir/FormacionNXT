package com.example.springcrud.demo.Professor.Application.Dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InputProfessorDto {

    String branch;

    String comments;

    int person_id;

}
