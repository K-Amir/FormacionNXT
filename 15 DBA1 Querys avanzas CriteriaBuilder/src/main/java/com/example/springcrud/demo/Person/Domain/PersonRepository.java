package com.example.springcrud.demo.Person.Domain;



import java.util.HashMap;
import java.util.List;
import java.util.Optional;

// Those are the methods we will need
public interface PersonRepository  {
    List<Person> findByName(String name);

    List<Person> getData(HashMap<String, Object> conditions);

    List<Person> findByNameContaining(String name);

    Optional<Person> findOneById(Integer id);

    List<Person> findAll();

    Person savePersonToDb(Person person);

    void deletePersonById(Integer id);
}
