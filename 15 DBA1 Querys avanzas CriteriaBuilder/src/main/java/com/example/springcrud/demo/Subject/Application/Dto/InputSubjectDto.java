package com.example.springcrud.demo.Subject.Application.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@NoArgsConstructor
public class InputSubjectDto {
    String comments;

    Date finish_date;

    Date initial_date;

    String subject;

}
