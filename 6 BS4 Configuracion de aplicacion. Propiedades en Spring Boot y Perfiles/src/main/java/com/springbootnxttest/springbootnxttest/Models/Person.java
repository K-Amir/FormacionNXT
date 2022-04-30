package com.springbootnxttest.springbootnxttest.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Person {
    private String name;
    private int age;
    private String email;
    private int id;


}
