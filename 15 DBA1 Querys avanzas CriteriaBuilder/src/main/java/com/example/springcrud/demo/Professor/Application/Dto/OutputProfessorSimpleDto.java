package com.example.springcrud.demo.Professor.Application.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OutputProfessorSimpleDto {
    String id;

    String branch;

    String comments;

}
