package com.example.springcrud.demo.Student.Infrastructure.Jpa;

import com.example.springcrud.demo.Student.Domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ImportedStudentJpaRepository extends JpaRepository<Student, Long> {

    @Query("SELECT u FROM Student  u WHERE u.person.id = ?1")
    Optional<Student> findStudentByPersonId(Integer personId);

}
