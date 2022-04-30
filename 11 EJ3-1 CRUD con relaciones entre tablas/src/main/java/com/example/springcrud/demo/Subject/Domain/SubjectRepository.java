package com.example.springcrud.demo.Subject.Domain;



import com.example.springcrud.demo.Subject.Application.Dto.InputSubjectDto;

import java.util.List;
import java.util.Optional;

public interface SubjectRepository {
    Optional<Subject> findOneSubjectById(Long id);

    List<Subject> findAllSubjects();

    Subject saveSubjectToDb(Subject subject);

    void deleteSubjectById(Long id);

    Optional<Subject> updateSubjectById(InputSubjectDto subject, Long id);
}
