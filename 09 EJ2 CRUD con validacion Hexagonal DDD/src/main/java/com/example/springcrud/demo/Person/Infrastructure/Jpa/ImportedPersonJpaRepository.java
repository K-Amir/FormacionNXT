package com.example.springcrud.demo.Person.Infrastructure.Jpa;

import com.example.springcrud.demo.Person.Domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Jpa repository
public interface ImportedPersonJpaRepository extends JpaRepository<Person, Integer> {
    List<Person> findByName(String name);

    List<Person> findByNameContaining(String name);

}
