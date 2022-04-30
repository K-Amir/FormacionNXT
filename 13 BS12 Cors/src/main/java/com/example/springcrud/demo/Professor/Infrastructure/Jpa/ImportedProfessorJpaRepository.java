package com.example.springcrud.demo.Professor.Infrastructure.Jpa;

import com.example.springcrud.demo.Professor.Domain.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ImportedProfessorJpaRepository extends JpaRepository<Professor, Long> {
    @Query("SELECT u FROM Professor  u WHERE u.person.id = ?1")
    Optional<Professor> findProfessorByPersonId(Integer personId);
}
