package com.example.springcrud.demo.Subject.Infrastructure;

import com.example.springcrud.demo.Subject.Application.Dto.InputSubjectDto;
import com.example.springcrud.demo.Subject.Domain.Subject;
import com.example.springcrud.demo.Subject.Domain.SubjectRepository;
import com.example.springcrud.demo.Subject.Infrastructure.Jpa.ImportedSubjectJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectJpaRepository implements SubjectRepository {
    private final ImportedSubjectJpaRepository subjectRepo;

    @Autowired
    public SubjectJpaRepository(ImportedSubjectJpaRepository subjectRepo) {
        this.subjectRepo = subjectRepo;
    }

    @Override
    public Optional<Subject> findOneSubjectById(Long id) {
        return subjectRepo.findById(id);
    }

    @Override
    public List<Subject> findAllSubjects() {
        return subjectRepo.findAll();
    }

    @Override
    public Subject saveSubjectToDb(Subject subject) {
        return subjectRepo.save(subject);
    }

    @Override
    public void deleteSubjectById(Long id) {
        subjectRepo.deleteById(id);
    }

    @Override
    public Optional<Subject> updateSubjectById(InputSubjectDto subject, Long id) {
        var subjectToUpdate = findOneSubjectById(id);
        if(subjectToUpdate.isEmpty()){
            throw  new RuntimeException("Subject not found");
        }

        subjectToUpdate.get().setSubject(subject.getSubject());
        subjectToUpdate.get().setComments(subject.getComments());
        subjectToUpdate.get().setFinish_date(subject.getFinish_date());
        subjectToUpdate.get().setInitial_date(subject.getInitial_date());

        saveSubjectToDb(subjectToUpdate.get());

        return subjectToUpdate;

    }
}
