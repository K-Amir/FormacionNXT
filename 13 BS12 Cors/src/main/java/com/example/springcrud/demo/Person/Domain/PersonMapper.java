package com.example.springcrud.demo.Person.Domain;

import com.example.springcrud.demo.Person.Application.Dtos.OutputPersonWithProfessorDto;
import com.example.springcrud.demo.Person.Application.Dtos.OutputPersonWithStudentDto;
import com.example.springcrud.demo.Person.Application.Dtos.personInDto;
import com.example.springcrud.demo.Person.Application.Dtos.personOutDto;
import com.example.springcrud.demo.Professor.Domain.Professor;
import com.example.springcrud.demo.Student.Domain.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    personOutDto personToPersonOutDto(Person person);

    Person personInDtoToPerson(personInDto person);

    List<personOutDto> personListToPersonOutDto(List<Person> persons);

    @Mapping(source = "person.id",target = "id")
    OutputPersonWithProfessorDto personToPersonWithProfessor(Person person, Professor professor);

    @Mapping(source = "person.id",target = "id")
    OutputPersonWithStudentDto personToPersonWithStudent(Person person, Student student);



}
