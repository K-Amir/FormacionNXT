package com.nxtexercises.jv2;


import java.util.List;

public class Startup {
  public static void main(String[] args) {
    CsvFileReader csvFileReader =
        new CsvFileReader(
            "C:\\Users\\amir.khalilov\\IdeaProjects\\EjerciciosNXT\\target\\Data.txt");
    List<Persona> result = csvFileReader.getData().toList();
  }
}
