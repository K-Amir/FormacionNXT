package com.nxtexercises.mappinglibraries.Models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Car {
    String color;
    String make;
    CarType type;
    int ruedas;
}
