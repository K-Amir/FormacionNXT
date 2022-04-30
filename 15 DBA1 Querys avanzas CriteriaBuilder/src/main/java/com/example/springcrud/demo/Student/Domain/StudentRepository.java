package com.example.springcrud.demo.Student.Domain;

import com.example.springcrud.demo.Student.Application.Dto.InputStudentDto;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {

    Optional<Student> findOneById(Long id);

    List<Student> findAll();

    Student saveStudentToDb(Student student);

    void deleteStudentById(Long id);

    Optional<Student> updateStudent(InputStudentDto student, Long id);

    Optional<Student> findStudentByPersonId(Integer personId);


}
