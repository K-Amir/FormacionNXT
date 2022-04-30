package com.mongodbcrud.mongodbcrud.Application;

import com.mongodbcrud.mongodbcrud.Application.Dtos.PersonInDto;
import com.mongodbcrud.mongodbcrud.Application.Dtos.PersonOutDto;
import com.mongodbcrud.mongodbcrud.Domain.Person;
import com.mongodbcrud.mongodbcrud.Domain.PersonRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
public class PersonController {
    private final PersonRepository personRepository;

    @Autowired
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPersonById(@PathVariable String id){
        Person p = personRepository.findOneById(id);
        PersonOutDto output = PersonMapper.INSTANCE.personToPersonOutDto(p);
        return ResponseEntity.ok().body(output);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePersonById(@PathVariable String id, @RequestBody PersonInDto personInDto){
        Person p = PersonMapper.INSTANCE.personInDtoToPerson(personInDto);
        personRepository.updatePerson(p,id);
        return ResponseEntity.ok().body("Updated succesfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id){
       personRepository.deleteById(id);
        return ResponseEntity.ok().body("Deleted succesfully");
    }

    @PostMapping()
    public ResponseEntity<?> addPerson(@RequestBody PersonInDto personInDto){
         Person p = PersonMapper.INSTANCE.personInDtoToPerson(personInDto);
         personRepository.savePerson(p);
        return ResponseEntity.ok().body("Saved succesfully");
    }
}
