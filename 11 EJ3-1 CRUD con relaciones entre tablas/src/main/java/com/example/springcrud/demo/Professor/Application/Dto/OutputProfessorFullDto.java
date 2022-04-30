package com.example.springcrud.demo.Professor.Application.Dto;

import com.example.springcrud.demo.Professor.Domain.Professor;
import com.example.springcrud.demo.Student.Application.Dto.OutputStudentFullDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class OutputProfessorFullDto {
    long id_professor;
    String comments;
    int id_persona;
    String branch;
    String user;
    String password;
    String name;
    String surname;
    String company_email;
    String personal_email;
    String city;
    boolean active;
    Date created_date;
    String imagen_url;
    Date termination_date;

    public OutputProfessorFullDto(Professor professor){
        this.branch = professor.getBranch();
        this.id_professor = professor.getId();
        this.comments = professor.getComments();
        this.id_persona = professor.getPerson().getId();
        this.user = professor.getPerson().getUser();
        this.password = professor.getPerson().getPassword();
        this.name = professor.getPerson().getName();
        this.surname = professor.getPerson().getSurname();
        this.company_email = professor.getPerson().getCompany_email();
        this.personal_email = professor.getPerson().getPersonal_email();
        this.city = professor.getPerson().getCity();
        this.active = professor.getPerson().getActive();
        this.created_date = professor.getPerson().getCreated_date();
        this.imagen_url = professor.getPerson().getImage_url();
        this.termination_date = professor.getPerson().getTermination_date();
    }
}
