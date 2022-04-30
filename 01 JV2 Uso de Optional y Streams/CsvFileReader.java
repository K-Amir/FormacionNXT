package com.nxtexercises.jv2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class CsvFileReader {
  private BufferedReader reader;
  private List<Persona> list = new ArrayList<>();

  public CsvFileReader(String filePath) {
    try {
      this.reader = new BufferedReader(new FileReader(filePath));
    } catch (FileNotFoundException e) {
      System.out.println(e.getMessage());
    }
  }

  public Stream<Persona> getData() {
    Stream<Persona> filteredList = null;
    AtomicInteger index = new AtomicInteger(1);
    try {
      String line;
      while ((line = reader.readLine()) != null) {
        String name = getValue(line.split(":"), 0).orElse("Desconocido");
        String city = getValue(line.split(":"), 1).orElse("Desconocida");
        String age = getValue(line.split(":"), 2).orElse("Desconocida");
        list.add(new Persona(name, city, age));

        filteredList =
            list.stream()
                .filter(
                    x -> {
                      try {
                        return Integer.parseInt(x.age()) < 25;
                      } catch (NumberFormatException e) {
                        return false;
                      }
                    })
                .peek(
                    e -> {
                      System.out.println("Line " + index.get() + " " + e);
                      index.getAndIncrement();
                    });
      }
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
    return filteredList;
  }

  public Optional<String> getValue(String[] data, int index) {
    try {
      if (data[index].isEmpty()) return Optional.empty();
      return Optional.of(data[index]);
    } catch (IndexOutOfBoundsException e) {
      return Optional.empty();
    }
  }
}
