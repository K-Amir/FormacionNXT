package com.springbootnxttest.springbootnxttest.Controllers;

import com.springbootnxttest.springbootnxttest.Models.Person;
import com.springbootnxttest.springbootnxttest.Services.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("/url/persons")
public class CrudController {
  @Autowired() private ModelService<ArrayList<Person>> personService;

  @PostMapping()
  public ResponseEntity<String> addPerson(@RequestBody Person person) {
    var persons = personService.getModel();
    if (persons == null) {
      persons = new ArrayList<>();
      persons.add(person);
      personService.setModel(persons);
    } else {
      persons.add(person);
    }
    return new ResponseEntity<String>("Added succesfully", HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<String> updatePerson(@PathVariable int id, @RequestBody Person person) {
    var persons = personService.getModel();
    for(Person p: persons){
      if(p.getId() == id){
        p.setAge(person.getAge());
        p.setEmail(person.getEmail());
        p.setName(person.getName());
      }
    }
    personService.setModel(persons);
    return new ResponseEntity<>("Updated succesfully", HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deletePerson(@PathVariable int id){
    var persons = personService.getModel();
    var filteredPersons = persons.stream().filter(p -> p.getId() != id);
    personService.setModel((ArrayList<Person>) filteredPersons.toList());
    return new ResponseEntity<>("Person deleted succesfully", HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Person> getPersonById(@PathVariable int id){
    var persons = personService.getModel();
    var filteredPerson = persons.stream().filter(p -> p.getId() == id).findFirst();
    if(filteredPerson.isPresent()){
      return new ResponseEntity<>(filteredPerson.get(), HttpStatus.OK);
    }
    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
  }
}
