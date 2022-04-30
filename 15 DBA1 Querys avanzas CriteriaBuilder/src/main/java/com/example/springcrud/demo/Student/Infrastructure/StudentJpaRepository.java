package com.example.springcrud.demo.Student.Infrastructure;

import com.example.springcrud.demo.Student.Application.Dto.InputStudentDto;
import com.example.springcrud.demo.Student.Domain.Student;
import com.example.springcrud.demo.Student.Domain.StudentRepository;
import com.example.springcrud.demo.Student.Infrastructure.Jpa.ImportedStudentJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentJpaRepository implements StudentRepository {
    final ImportedStudentJpaRepository studentRepo;


    @Autowired
    public StudentJpaRepository(ImportedStudentJpaRepository personRepo) {
        this.studentRepo = personRepo;
    }

    @Override
    public Optional<Student> findOneById(Long id) {
        return studentRepo.findById(id);
    }

    @Override
    public List<Student> findAll() {
        return studentRepo.findAll();
    }

    @Override
    public Student saveStudentToDb(Student student) {

        return studentRepo.saveAndFlush(student);
    }

    @Override
    public void deleteStudentById(Long id) {
        studentRepo.deleteById(id);
    }



    @Override
    public Optional<Student> updateStudent(InputStudentDto student, Long id) {
        Optional<Student> studentToUpdate = findOneById(id);

        if(studentToUpdate.isEmpty()) return Optional.empty();

         studentToUpdate.get().setBranch(student.getBranch());
         studentToUpdate.get().setComments(student.getComments());
         studentToUpdate.get().setNumHoursWeek(student.getNumHoursWeek());

         saveStudentToDb(studentToUpdate.get());

         return studentToUpdate;


    }

    @Override
    public Optional<Student> findStudentByPersonId(Integer personId) {
        return studentRepo.findStudentByPersonId(personId);
    }


}
