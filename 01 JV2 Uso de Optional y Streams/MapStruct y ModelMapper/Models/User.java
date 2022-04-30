package com.nxtexercises.mappinglibraries.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String email;
    private String username;
    private String password;
    private String address;
}
