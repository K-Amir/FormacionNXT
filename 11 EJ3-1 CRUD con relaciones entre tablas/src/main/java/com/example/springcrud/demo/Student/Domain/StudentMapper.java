package com.example.springcrud.demo.Student.Domain;

import com.example.springcrud.demo.Student.Application.Dto.InputStudentDto;
import com.example.springcrud.demo.Student.Application.Dto.OutputStudentSimpleDto;
import org.mapstruct.Mapper;

import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudentMapper {

    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    OutputStudentSimpleDto studentToSimpleStudentDto(Student student);

    @Mapping(source = "numHoursWeek", target = "numHoursWeek")
    Student inputStudentToStudent(InputStudentDto inputStudentDto);


}
