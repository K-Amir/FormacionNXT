package com.mongodbcrud.mongodbcrud.Application.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class PersonInDto {
    private String user;

    private String password;

    private String name;

    private  String surname;

    private String company_email;

    private String personal_email;

    private String city;

    private Boolean active;

    private String image_url;

    private  Date termination_date;
}
