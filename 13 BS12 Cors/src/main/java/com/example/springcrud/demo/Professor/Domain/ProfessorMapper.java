package com.example.springcrud.demo.Professor.Domain;

import com.example.springcrud.demo.Professor.Application.Dto.InputProfessorDto;
import com.example.springcrud.demo.Professor.Application.Dto.OutputProfessorFullDto;
import com.example.springcrud.demo.Professor.Application.Dto.OutputProfessorSimpleDto;
import com.example.springcrud.demo.Professor.Application.Dto.OutputProfessorStudentsDto;
import com.example.springcrud.demo.Student.Domain.Student;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProfessorMapper {
    ProfessorMapper INSTANCE = Mappers.getMapper(ProfessorMapper.class);

    Professor inputProfessorToProfessor(InputProfessorDto inputProfessorDto);

    OutputProfessorSimpleDto professorToSimpleProfessorDto(Professor professor);

    List<OutputProfessorSimpleDto> professorToSimpleOutput(List<Professor> professsors);

    List<OutputProfessorStudentsDto> professorStudentsToProfessorStudentsDto(List<Student> students);
}
