package com.feignresttemplate.feignresttemplate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorDto {
    String id;

    String branch;

    String comments;
}
