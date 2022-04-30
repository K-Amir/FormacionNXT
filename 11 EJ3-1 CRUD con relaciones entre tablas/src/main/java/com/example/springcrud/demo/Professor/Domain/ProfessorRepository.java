package com.example.springcrud.demo.Professor.Domain;

import com.example.springcrud.demo.Professor.Application.Dto.InputProfessorDto;
import java.util.List;
import java.util.Optional;

public interface ProfessorRepository {
    Optional<Professor> findOneProfessorById(long id);

    List<Professor> findAll();

    Professor saveProfessorToDb(Professor professor);

    void deleteProfessorById(long id);

    Optional<Professor> updateProfessor(InputProfessorDto professor, long id);

    Optional<Professor> findProfessorByPersonId(Integer personId);
}
