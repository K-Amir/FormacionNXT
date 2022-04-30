package com.example.springcrud.demo.Subject.Infrastructure.Jpa;

import com.example.springcrud.demo.Subject.Domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImportedSubjectJpaRepository extends JpaRepository<Subject, Long> {
}
