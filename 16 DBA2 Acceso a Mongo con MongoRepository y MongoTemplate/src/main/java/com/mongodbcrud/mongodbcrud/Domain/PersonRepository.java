package com.mongodbcrud.mongodbcrud.Domain;

import org.bson.types.ObjectId;

import java.util.List;

public interface PersonRepository {
    Person savePerson(Person person);

    List<Person> getAllPersons();

    Person findOneById(String id);

    void deleteById(String id);

    void updatePerson(Person person, String id);


}
