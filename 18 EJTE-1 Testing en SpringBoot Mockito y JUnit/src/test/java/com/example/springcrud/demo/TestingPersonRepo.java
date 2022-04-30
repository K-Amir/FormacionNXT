package com.example.springcrud.demo;


import com.example.springcrud.demo.Person.Domain.Person;
import com.example.springcrud.demo.Person.Infrastructure.Jpa.ImportedPersonJpaRepository;
import com.example.springcrud.demo.Person.Infrastructure.PersonJpaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestingPersonRepo {

    Person p = new Person(1, "skhal", "secret", "amir", "khalilov", "non@g", "no", "moscow", true, null, "no", null);

    @InjectMocks
    PersonJpaRepository personRepository;

    @Mock
    ImportedPersonJpaRepository importedPersonJpaRepository;

    @Test
    void findById() {
        when(importedPersonJpaRepository.findById(1))
                .thenReturn(Optional.of(p));
        Optional<Person> person = personRepository.findOneById(1);
      Assertions.assertEquals(person.get(), p);
    }

    @Test
    void findByName(){
        List<Person> persons = new ArrayList<>();
        persons.add(p);
        when(importedPersonJpaRepository.findByName("name")).thenReturn(persons);

        List<Person> persosnFromRepo =  personRepository.findByName("name");
        Assertions.assertEquals(persosnFromRepo, persons);
    }

    @Test
    void findAllPersons(){
        List<Person> persons = new ArrayList<>();
        persons.add(p);

        when(importedPersonJpaRepository.findAll()).thenReturn(persons);

        List<Person> personsFromRepo =  personRepository.findAll();
        Assertions.assertEquals(personsFromRepo, persons);
    }

    @Test
    void findByNmaeContaining(){
        List<Person> persons = new ArrayList<>();
        persons.add(p);
        when(importedPersonJpaRepository.findByNameContaining("name")).thenReturn(persons);

        List<Person> personsFound  = personRepository.findByNameContaining("name");
        Assertions.assertEquals(personsFound, persons);

    }

    @Test
    void testSavePerson(){
        when(importedPersonJpaRepository.saveAndFlush(p)).thenReturn(p);

        Person person = personRepository.savePersonToDb(p);

        Assertions.assertEquals(p, person);
    }

    @Test
    void deletePersonById(){
        importedPersonJpaRepository.save(p);

        personRepository.deletePersonById(1);

        Person person = importedPersonJpaRepository.getById(1);
        Assertions.assertEquals(person , null);


    }
}
