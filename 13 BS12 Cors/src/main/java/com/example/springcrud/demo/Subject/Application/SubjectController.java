package com.example.springcrud.demo.Subject.Application;


import com.example.springcrud.demo.Student.Application.Dto.RequestFeedback;
import com.example.springcrud.demo.Student.Domain.Student;
import com.example.springcrud.demo.Student.Domain.StudentRepository;
import com.example.springcrud.demo.Subject.Application.Dto.InputSubjectDto;
import com.example.springcrud.demo.Subject.Domain.SubjectMapper;
import com.example.springcrud.demo.Subject.Domain.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subject")
public class SubjectController {
    private final SubjectRepository subjectRepo;
    private final StudentRepository studentRepo;

    @Autowired
    public SubjectController(SubjectRepository subjectRepo, StudentRepository studentRepo) {
        this.subjectRepo = subjectRepo;
        this.studentRepo = studentRepo;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSubjectById(@PathVariable String id) {
        var subject = subjectRepo.findOneSubjectById(Long.parseLong(id));
        if (subject.isPresent()) {
            var output = SubjectMapper.INSTANCE.subjectToOutputSubjectDto(subject.get());
            return ResponseEntity.ok().body(output);
        }
        return sendFeedBack("No subject found with the specified id", 404);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllSubjects() {
        var subjects = subjectRepo.findAllSubjects();
        return ResponseEntity.ok().body(subjects);
    }

    @PostMapping()
    public ResponseEntity<?> addSubject(@RequestBody InputSubjectDto inputSubjectDto) {
        var subject = SubjectMapper.INSTANCE.inputSubjectDtoToSubject(inputSubjectDto);
        subjectRepo.saveSubjectToDb(subject);
        return sendFeedBack("Subject created successfully", 200);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSubjectById(@PathVariable String id) {
        try {
            var students = studentRepo.findAll();
            checkIfAnyStudentIsAssignedToSubjectToDelete(id, students);
            subjectRepo.deleteSubjectById(Long.parseLong(id));
        } catch (Exception ex) {
            ex.printStackTrace();
            return sendFeedBack("Cannot delete subject with the specified id", 500);

        }
        return sendFeedBack("Subject deleted successfully", 200);
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<?> getSubjectsFromStudent(@PathVariable String id) {
        var student = studentRepo.findOneById(Long.parseLong(id));
        if (student.isEmpty()) {
            return sendFeedBack("No student found with the specified id", 500);
        }
        var studentSubjects = student.get().getSubjects();
        var output = SubjectMapper.INSTANCE.subjectListToOutputSubjectListDto(studentSubjects);
        return ResponseEntity.ok().body(output);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatedSubjectById(@RequestBody InputSubjectDto inputSubjectDto, @PathVariable String id) {
        try {
            subjectRepo.updateSubjectById(inputSubjectDto, Long.parseLong(id));
        } catch (Exception ex) {
            ex.printStackTrace();
            return sendFeedBack("Error while updating subject", 500);
        }
        return sendFeedBack("Subject updated successfully", 200);
    }


    private ResponseEntity<?> sendFeedBack(String message, int status) {
        return ResponseEntity.status(status).body(RequestFeedback.builder().message(message).build());
    }

    private void checkIfAnyStudentIsAssignedToSubjectToDelete(String id, List<Student> students) {
        students.forEach(student -> student.getSubjects().forEach(subject -> {
            if (subject.getId() == Long.parseLong(id)) {
                throw new RuntimeException("xd");
            }
        }));
    }


}
