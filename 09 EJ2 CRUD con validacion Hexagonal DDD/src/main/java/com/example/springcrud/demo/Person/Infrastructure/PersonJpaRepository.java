package com.example.springcrud.demo.Person.Infrastructure;

import com.example.springcrud.demo.Person.Domain.Person;
import com.example.springcrud.demo.Person.Domain.PersonRepository;
import com.example.springcrud.demo.Person.Infrastructure.Jpa.ImportedPersonJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// The repository itself working
@Service
public class PersonJpaRepository implements PersonRepository {

    private final ImportedPersonJpaRepository impl;

    @Autowired
    public PersonJpaRepository(ImportedPersonJpaRepository impl) {
        this.impl = impl;
    }

    @Override
    public List<Person> findByName(String name) {
       return impl.findByName(name);
    }

    @Override
    public List<Person> findByNameContaining(String name) {
       return  impl.findByNameContaining(name);
    }

    @Override
    public Optional<Person> findOneById(Integer id) {
        return impl.findById(id);
    }

    @Override
    public List<Person> findAll() {
       return  impl.findAll();
    }

    @Override
    public Person savePersonToDb(Person person) {
        return  impl.saveAndFlush(person);
    }

    @Override
    public void deletePersonById(Integer id) {
         impl.deleteById(id);
    }
}
