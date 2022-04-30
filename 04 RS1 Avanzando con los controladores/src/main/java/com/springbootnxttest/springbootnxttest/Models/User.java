package com.springbootnxttest.springbootnxttest.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class User {
    private String name;
    private int age;
    private String city;

}
