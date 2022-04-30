package com.example.springcrud.demo.Student.Application.Dto;

import com.example.springcrud.demo.Student.Domain.Student;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class OutputStudentFullDto {
    long id_student;
    int num_hours_week;
    String comments;
    int id_persona;
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

    public OutputStudentFullDto(Student student){
        this.id_student = student.getId();
        this.num_hours_week = student.getNumHoursWeek();
        this.comments = student.getComments();
        this.id_persona = student.getPerson().getId();
        this.user = student.getPerson().getUser();
        this.password = student.getPerson().getPassword();
        this.name = student.getPerson().getName();
        this.surname = student.getPerson().getSurname();
        this.company_email = student.getPerson().getCompany_email();
        this.personal_email = student.getPerson().getPersonal_email();
        this.city = student.getPerson().getCity();
        this.active = student.getPerson().getActive();
        this.created_date = student.getPerson().getCreated_date();
        this.imagen_url = student.getPerson().getImage_url();
        this.termination_date = student.getPerson().getTermination_date();

    }
}
