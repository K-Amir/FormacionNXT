package com.mongodbcrud.mongodbcrud.Infrastructure;

import com.mongodbcrud.mongodbcrud.Application.PersonMapper;
import com.mongodbcrud.mongodbcrud.Domain.Person;
import com.mongodbcrud.mongodbcrud.Domain.PersonRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
public class PersonRepositoryMongo implements PersonRepository {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public PersonRepositoryMongo(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Person savePerson(Person person) {
        mongoTemplate.save(person);
        return person;
    }

    @Override
    public List<Person> getAllPersons() {
        return mongoTemplate.findAll(Person.class);
    }

    @Override
    public Person findOneById(String id) {
        ObjectId objectId = new ObjectId(id);
        return mongoTemplate.findById(objectId, Person.class);


    }

    @Override
    public void deleteById(String id) {
        ObjectId objectId = new ObjectId(id);
        Person personToRemove = mongoTemplate.findById(objectId, Person.class);
        if(personToRemove != null){
            mongoTemplate.remove(personToRemove);
        }

    }

    @Override
    public void updatePerson(Person person, String id) {
         person.set_id(new ObjectId(id));

         mongoTemplate.save(person);

    }
}
