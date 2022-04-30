package com.example.springcrud.demo.Professor.Application;


import com.example.springcrud.demo.Person.Domain.Person;
import com.example.springcrud.demo.Person.Domain.PersonRepository;
import com.example.springcrud.demo.Professor.Application.Dto.InputProfessorDto;
import com.example.springcrud.demo.Professor.Application.Dto.OutputProfessorFullDto;
import com.example.springcrud.demo.Professor.Application.Dto.OutputProfessorSimpleDto;
import com.example.springcrud.demo.Professor.Application.Dto.OutputProfessorStudentsDto;
import com.example.springcrud.demo.Professor.Domain.Professor;
import com.example.springcrud.demo.Professor.Domain.ProfessorMapper;
import com.example.springcrud.demo.Professor.Domain.ProfessorRepository;
import com.example.springcrud.demo.Student.Application.Dto.RequestFeedback;
import com.example.springcrud.demo.Student.Domain.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    final ProfessorRepository professorRepo;
    final PersonRepository personRepo;
    final StudentRepository studentRepo;



    @Autowired
    public ProfessorController(ProfessorRepository professorRepo, PersonRepository personRepo, StudentRepository studentRepo) {
        this.professorRepo = professorRepo;
        this.personRepo = personRepo;
        this.studentRepo = studentRepo;
    }

    @GetMapping("all")
    public ResponseEntity<?> getal(){
        List<OutputProfessorSimpleDto> professors =  ProfessorMapper.INSTANCE.professorToSimpleOutput(professorRepo.findAll());
        return ResponseEntity.ok().body(professors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findStudentById(@PathVariable String id, @PathParam("outputType") String outputType) {
        Optional<Professor> professor = professorRepo.findOneProfessorById(Long.parseLong(id));

        if (professor.isEmpty()) return sendFeedBack("There's no professor with the specified id", 500);

        if (outputType != null && outputType.equals("full")) {
            OutputProfessorFullDto output = new OutputProfessorFullDto(professor.get());
            return ResponseEntity.ok().body(output);

        }

        OutputProfessorSimpleDto output =  ProfessorMapper.INSTANCE.professorToSimpleProfessorDto(professor.get());
        return ResponseEntity.ok().body(output);

    }

    @GetMapping("/{id}/students")
    public ResponseEntity<?> getStudentsFromTheProfessor(@PathVariable String id){
        Optional<Professor> professor = professorRepo.findOneProfessorById(Long.parseLong(id));

        if(professor.isEmpty()) return sendFeedBack("There's no professor with the specified id", 500);


        List<OutputProfessorStudentsDto> professorStudentsDtos
                = ProfessorMapper.INSTANCE.professorStudentsToProfessorStudentsDto(professor.get().getStudents());

        return  ResponseEntity.ok().body(professorStudentsDtos);
    }


    @PostMapping
    public ResponseEntity<?> createStudent(@RequestBody InputProfessorDto inputProfessorDto) {
        try {

            checkPersonIdReferenceIsUnique(inputProfessorDto);

            Professor professor = ProfessorMapper.INSTANCE.inputProfessorToProfessor(inputProfessorDto);

            Optional<Person> person = personRepo.findOneById(inputProfessorDto.getPerson_id());

            person.ifPresent(professor::setPerson);

            professorRepo.saveProfessorToDb(professor);

            return sendFeedBack("Professor saved succesfully", 200);
        } catch (Exception e) {

            e.printStackTrace();

            return sendFeedBack("There's already an entity assigned to this person id", 500);

        }


    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteStudentById(@PathVariable String id) {

        try {
            professorRepo.deleteProfessorById(Long.parseLong(id));
            return sendFeedBack("Professor deleted sucesfully", 200);

        } catch (Exception e) {
            return sendFeedBack("There's not professor with the specified Id", 500);
        }

    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateStudentById(@RequestBody InputProfessorDto inputProfessorDto, @PathVariable String id) {

        Optional<Professor> professor = professorRepo.updateProfessor(inputProfessorDto, Long.parseLong(id));


        if (professor.isEmpty()) {
            return sendFeedBack("Error while updating", 500);
        }

        return sendFeedBack("Updated sucesfully", 200);


    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }


    private ResponseEntity<?> sendFeedBack(String message, int status) {
        return ResponseEntity.status(status).body(RequestFeedback.builder().message(message).build());
    }

    private void checkPersonIdReferenceIsUnique(InputProfessorDto inputProfessorDto){
        var professor = professorRepo.findProfessorByPersonId(inputProfessorDto.getPerson_id());

        if(professor.isPresent()){
            throw new RuntimeException("There's already an entity assigned to this person id");
        }

        studentRepo.findAll().forEach((student)->{
            if(student.getPerson().getId() == inputProfessorDto.getPerson_id()){
               throw new RuntimeException("There's already an entity assigned to this person id");
            }
        });

    }


}
