package com.example.springcrud.demo.Person.Application;

import com.example.springcrud.demo.Person.Application.Dtos.OutputPersonWithProfessorDto;
import com.example.springcrud.demo.Person.Application.Dtos.OutputPersonWithStudentDto;
import com.example.springcrud.demo.Person.Application.Dtos.personInDto;
import com.example.springcrud.demo.Person.Application.Dtos.personOutDto;
import com.example.springcrud.demo.Person.Application.ErrorHandling.NotFoundException;
import com.example.springcrud.demo.Person.Application.ErrorHandling.UnprocesableException;
import com.example.springcrud.demo.Person.Domain.Person;
import com.example.springcrud.demo.Person.Domain.PersonMapper;
import com.example.springcrud.demo.Person.Domain.PersonRepository;
import com.example.springcrud.demo.Professor.Application.Dto.OutputProfessorSimpleDto;
import com.example.springcrud.demo.Professor.Domain.Professor;
import com.example.springcrud.demo.Professor.Domain.ProfessorMapper;
import com.example.springcrud.demo.Professor.Domain.ProfessorRepository;
import com.example.springcrud.demo.Student.Application.Dto.RequestFeedback;
import com.example.springcrud.demo.Student.Domain.Student;
import com.example.springcrud.demo.Student.Domain.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.websocket.server.PathParam;
import java.util.Optional;


@RestController
@RequestMapping("/person")
public class PersonController {
    private final PersonRepository personRepo;
    private final StudentRepository studentRepo;
    private final ProfessorRepository professorRepo;

    @Autowired
    public PersonController(PersonRepository personRepo, StudentRepository studentRepo, ProfessorRepository professorRepo) {
        this.personRepo = personRepo;
        this.studentRepo = studentRepo;
        this.professorRepo = professorRepo;
    }


    // Feign exercise
    @GetMapping("/professor/{id}")
    public ResponseEntity<?> getProfessor(@PathVariable int id)
    {
       Optional<Professor> p = professorRepo.findProfessorByPersonId(id);
       if(p.isPresent()){
          OutputProfessorSimpleDto output = ProfessorMapper.INSTANCE.professorToSimpleProfessorDto(p.get());
          return ResponseEntity.ok().body(output);
       }

       return sendFeedBack("Not found",404);
    }

    // This controller fails at the second request due to a Spring bug.
    @GetMapping()
    public ResponseEntity<?> findPersonByName(@PathParam("name") String name) {
        var p = personRepo.findByNameContaining(name);
        if (!p.isEmpty()) {
            return ResponseEntity.ok().body(p);
        } else {
            return new ResponseEntity<>("Not found by that name", HttpStatus.NOT_ACCEPTABLE);
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
    public ResponseEntity<?> findPersonById(@PathVariable int id, @PathParam("outputType") String outputType) {
        var person = personRepo.findOneById(id);
        if (person.isEmpty()) {
            throw new NotFoundException("User not found");
        }

        if(isOutputSpecified(outputType)){

            Optional<Student> student = studentRepo.findStudentByPersonId(person.get().getId());
            if(student.isPresent()){
                var output =
                        PersonMapper.INSTANCE.personToPersonWithStudent(person.get(),student.get());
                return ResponseEntity.ok().body(output);
            }

            Optional<Professor> professor = professorRepo.findProfessorByPersonId(person.get().getId());
            if(professor.isPresent()){
                var output = PersonMapper.INSTANCE.personToPersonWithProfessor(person.get(), professor.get());
                return  ResponseEntity.ok().body(output);
            }

        }

        return ResponseEntity.ok().body(PersonMapper.INSTANCE.personToPersonOutDto(person.get()));


    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deletePersonById(@PathVariable int id){
        try{

            Optional<Student> student = studentRepo.findStudentByPersonId(id);
            Optional<Professor> professor = professorRepo.findProfessorByPersonId(id);

            if(student.isPresent() || professor.isPresent()){
                return sendFeedBack("Can not delete person if a entity belongs to it",500);
            }

            personRepo.deletePersonById(id);

            return sendFeedBack("Delete sucesfully",200);

        }catch (Exception e){

            e.printStackTrace();

            return ResponseEntity.badRequest().body("Failed to delete ");

        }
    }


    @PostMapping()
    public ResponseEntity<?> savePersonToDb(@RequestBody  personInDto personInDto) {
        System.out.println(personInDto.getName());
        if(personInDto.getName() == null || personInDto.getName().length() < 4 || personInDto.getName().length() > 10){
          throw new UnprocesableException("Failed to add user");
        }
        Person personToSave = PersonMapper.INSTANCE.personInDtoToPerson(personInDto);
        personRepo.savePersonToDb(personToSave);
        personOutDto personoutDto = PersonMapper.INSTANCE.personToPersonOutDto(personToSave);
        return ResponseEntity.ok().body(personoutDto);
    }




    private boolean isOutputSpecified(String outputType) {
        return outputType != null && outputType.equals("full");
    }

    private ResponseEntity<?> sendFeedBack(String message, int status) {
        return ResponseEntity.status(status).body(RequestFeedback.builder().message(message).build());
    }


}
