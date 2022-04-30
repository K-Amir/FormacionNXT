package com.example.springcrud.demo.Person.Infrastructure.Jpa;

import com.example.springcrud.demo.Person.Domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.HashMap;
import java.util.List;

// Jpa repository
public interface ImportedPersonJpaRepository extends JpaRepository<Person, Integer> {
    List<Person> findByName(String name);

    List<Person> findByNameContaining(String name);

    @Query("SELECT p FROM Person p where p.company_email = :email")
    Person findPersonByCompany_email(@Param("email") String email);



}
