package com.example.springcrud.demo.Professor.Infrastructure;


import com.example.springcrud.demo.Professor.Application.Dto.InputProfessorDto;
import com.example.springcrud.demo.Professor.Domain.Professor;
import com.example.springcrud.demo.Professor.Domain.ProfessorRepository;
import com.example.springcrud.demo.Professor.Infrastructure.Jpa.ImportedProfessorJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorJpaRepository implements ProfessorRepository {
    private final ImportedProfessorJpaRepository professorRepo;


    @Autowired
    public ProfessorJpaRepository(ImportedProfessorJpaRepository professorRepo) {
        this.professorRepo = professorRepo;
    }


    @Override
    public Optional<Professor> findOneProfessorById(long id) {
        return professorRepo.findById(id);
    }

    @Override
    public List<Professor> findAll() {
        return professorRepo.findAll();
    }

    @Override
    public Professor saveProfessorToDb(Professor professor)  {
        return professorRepo.save(professor);
    }

    @Override
    public void deleteProfessorById(long id) {
        professorRepo.deleteById(id);

    }

    @Override
    public Optional<Professor> updateProfessor(InputProfessorDto professor, long id) {
        Optional<Professor> profesorToUpdate = findOneProfessorById(id);

        if(profesorToUpdate.isEmpty()) return Optional.empty();

        profesorToUpdate.get().setBranch(professor.getBranch());
        profesorToUpdate.get().setComments(professor.getComments());


        saveProfessorToDb(profesorToUpdate.get());

        return profesorToUpdate;
    }

    @Override
    public Optional<Professor> findProfessorByPersonId(Integer personId) {
        return professorRepo.findProfessorByPersonId(personId);
    }
}
