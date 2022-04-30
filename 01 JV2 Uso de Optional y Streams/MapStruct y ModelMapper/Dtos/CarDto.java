package com.nxtexercises.mappinglibraries.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CarDto {
    private String color;
    private String make;
    private String type;
    private int wheels;

}
