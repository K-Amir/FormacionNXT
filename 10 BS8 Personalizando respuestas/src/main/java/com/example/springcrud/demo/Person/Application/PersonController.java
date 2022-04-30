package com.example.springcrud.demo.Person.Application;

import com.example.springcrud.demo.Person.Application.Dtos.personInDto;
import com.example.springcrud.demo.Person.Application.ErrorHandling.NotFoundException;
import com.example.springcrud.demo.Person.Application.ErrorHandling.UnprocesableException;
import com.example.springcrud.demo.Person.Domain.Person;
import com.example.springcrud.demo.Person.Domain.PersonMapper;
import com.example.springcrud.demo.Person.Domain.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.websocket.server.PathParam;


@RestController
@RequestMapping("/person")
public class PersonController {
    PersonRepository personRepo;

    @Autowired
    public PersonController(PersonRepository personRepo) {
        this.personRepo = personRepo;
    }


    // This controller fails at the second request due to a bug.
    @GetMapping()
    public ResponseEntity<?> findPersonByName(@PathParam("name") String name) {
        var p = personRepo.findByNameContaining(name);
        if (!p.isEmpty()) {
            return ResponseEntity.ok().body(p);
        } else {
            return new ResponseEntity<String>("Not found by that name", HttpStatus.NOT_ACCEPTABLE);
        }

    }

    @GetMapping("/all")
    public ResponseEntity<?> findAllPersons() {
        var p = personRepo.findAll();
        if (!p.isEmpty()) {
            return ResponseEntity.ok().body(PersonMapper.INSTANCE.personListToPersonOutDto(p.stream().toList()));
        }
        return ResponseEntity.ok().body(p);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findPersonById(@PathVariable int id) {
        var p = personRepo.findOneById(id);
        if (p.isPresent()) {
            return ResponseEntity.ok().body(PersonMapper.INSTANCE.personToPersonOutDto(p.get()));
        }

        throw new NotFoundException("User not found");

    }

    @PostMapping()
    public ResponseEntity<?> savePersonToDb(@RequestBody  personInDto personInDto) throws Exception {
        if(personInDto.getName() == null || personInDto.getName().length() < 6 || personInDto.getName().length() > 10){
          throw new UnprocesableException("Faild to add user");
        }
        Person personToSave = PersonMapper.INSTANCE.personInDtoToPerson(personInDto);
        return ResponseEntity.ok().body(personRepo.savePersonToDb(personToSave));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deletePersonById(@PathVariable int id){
        try{
            personRepo.deletePersonById(id);
            return ResponseEntity.ok().body("Deleted succesfully");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Failedto delete ");

        }
    }


}
