package com.example.springcrud.demo.Person.Application.Dtos;


import lombok.AllArgsConstructor;
import lombok.Data;


import java.util.Date;

@Data
@AllArgsConstructor
public class personOutDto {
    private Integer id;
    private String user;
    private String password;
    private String surname;
    private String company_email;
    private String personal_email;
    private String city;
    private Boolean active;
    private Date created_date;
    private String image_url;
    private Date termination_date;
    private boolean isAdmin;
}
