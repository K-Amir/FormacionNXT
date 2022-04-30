package com.example.springcrud.demo.Student.Application;

import com.example.springcrud.demo.Person.Domain.Person;
import com.example.springcrud.demo.Person.Domain.PersonRepository;
import com.example.springcrud.demo.Professor.Domain.Professor;
import com.example.springcrud.demo.Professor.Domain.ProfessorRepository;
import com.example.springcrud.demo.Student.Application.Dto.OutputStudentFullDto;
import com.example.springcrud.demo.Student.Application.Dto.OutputStudentSimpleDto;
import com.example.springcrud.demo.Student.Application.Dto.RequestFeedback;
import com.example.springcrud.demo.Student.Application.Dto.InputStudentDto;
import com.example.springcrud.demo.Student.Domain.Student;
import com.example.springcrud.demo.Student.Domain.StudentMapper;
import com.example.springcrud.demo.Student.Domain.StudentRepository;
import com.example.springcrud.demo.Subject.Domain.Subject;
import com.example.springcrud.demo.Subject.Domain.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.*;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentRepository studentRepo;
    private final PersonRepository personRepo;
    private final ProfessorRepository professorRepo;
    private final SubjectRepository subjectRepo;

    @Autowired
    public StudentController(StudentRepository studentRepo, PersonRepository personRepo, ProfessorRepository professorRepo, SubjectRepository subjectRepo) {
        this.studentRepo = studentRepo;
        this.personRepo = personRepo;
        this.professorRepo = professorRepo;
        this.subjectRepo = subjectRepo;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findStudentById(@PathVariable String id, @PathParam("outputType") String outputType) {
        Optional<Student> student = studentRepo.findOneById(Long.parseLong(id));
        if (student.isEmpty()) return sendFeedBack("Cannot find student with the specified id", 500);
        if (outputType.equals("full")) {
            OutputStudentFullDto output = new OutputStudentFullDto(student.get());
            return ResponseEntity.ok().body(output);
        }
        OutputStudentSimpleDto output = StudentMapper.INSTANCE.studentToSimpleStudentDto(student.get());
        return ResponseEntity.ok().body(output);
    }

    @PostMapping("/subject-assign/{id}")
    public ResponseEntity<?> assignSubjectsToUser(@PathVariable String id, @RequestBody List<Integer> subjectIds) {
        var student = studentRepo.findOneById(Long.parseLong(id));
        if (student.isEmpty()) return sendFeedBack("Cannot find student with the specified id", 500);
        List<Subject> updatedSubjectList = getStudentSubjectsAfterAssign(subjectIds);
        student.get().setSubjects(updatedSubjectList);
        studentRepo.saveStudentToDb(student.get());
        return sendFeedBack("Subjects added successfully", 200);
    }

    @PostMapping("/subject-detach/{id}")
    public ResponseEntity<?> detachSubjectFromStudent(@PathVariable String id, @RequestBody List<Integer> subjectIdsToDetach) {
        var student = studentRepo.findOneById(Long.parseLong(id));
        if (student.isEmpty()) return sendFeedBack("Cannot find student with the specified id", 500);
        List<Subject> updatedSubjectList = getStudentSubjectsAfterDetach(subjectIdsToDetach, student);
        student.get().setSubjects(updatedSubjectList);
        studentRepo.saveStudentToDb(student.get());
        return sendFeedBack("Student subjects detached successfully", 200);
    }

    @PostMapping
    public ResponseEntity<?> createStudent(@RequestBody InputStudentDto inputStudentDto) {
        try {
            checkPersonIdReferenceIsUnique(inputStudentDto);
            Student student = StudentMapper.INSTANCE.inputStudentToStudent(inputStudentDto);

            Optional<Person> person = personRepo.findOneById(inputStudentDto.getPerson_id());
            person.ifPresent(student::setPerson);

            Optional<Professor> professor = professorRepo.findProfessorByPersonId(inputStudentDto.getProfessor_id());
            professor.ifPresent(student::setProfessor);

            studentRepo.saveStudentToDb(student);
            return sendFeedBack("User created successfully", 200);
        } catch (Exception ex) {
            ex.printStackTrace();
            return sendFeedBack("There's already an entity assigned to this person id", 500);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteStudentById(@PathVariable String id) {
        try {
            studentRepo.deleteStudentById(Long.parseLong(id));
            return ResponseEntity.ok().body(RequestFeedback.builder().message("Student deleted successfully").build());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(RequestFeedback.builder().message("Could not delete that student").build());
        }

    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateStudentById(@RequestBody InputStudentDto inputStudentDto, @PathVariable String id) {
        Optional<Student> student = studentRepo.updateStudent(inputStudentDto, Long.parseLong(id));
        if (student.isEmpty()) {
            return sendFeedBack("Error while updating", 500);
        }
        return sendFeedBack("Updated successfully", 200);


    }

    private List<Subject> getStudentSubjectsAfterAssign(List<Integer> subjectIds) {
        List<Subject> subjects = new ArrayList<>();
        subjectIds.forEach(subjectId -> {
            var subject = subjectRepo.findOneSubjectById(Long.valueOf(subjectId));
            subject.ifPresent(subjects::add);
        });
        return subjects;
    }

    private List<Subject> getStudentSubjectsAfterDetach(List<Integer> subjectIds, Optional<Student> student) {
        List<Subject> updatedSubjectList = new ArrayList<>();
        subjectIds.forEach(subjectId -> student.get().getSubjects().forEach(subject -> {
            if (subject.getId() != Long.valueOf(subjectId)) {
                updatedSubjectList.add(subject);
            }
        }));
        return updatedSubjectList;
    }


    private ResponseEntity<?> sendFeedBack(String message, int status) {
        return ResponseEntity.status(status).body(RequestFeedback.builder().message(message).build());
    }

    private void checkPersonIdReferenceIsUnique(InputStudentDto inputStudentDto) {
        var student = studentRepo.findStudentByPersonId(inputStudentDto.getPerson_id());
        if (student.isPresent()) {
            throw new RuntimeException("There's already an entity assigned to this person id");
        }
        professorRepo.findAll().forEach((professor) -> {
            if (professor.getPerson().getId() == inputStudentDto.getPerson_id()) {
                throw new RuntimeException("There's already an entity assigned to this person id");
            }
        });
    }


}
