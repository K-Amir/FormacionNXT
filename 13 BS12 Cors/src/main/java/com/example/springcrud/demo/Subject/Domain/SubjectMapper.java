package com.example.springcrud.demo.Subject.Domain;

import com.example.springcrud.demo.Subject.Application.Dto.InputSubjectDto;
import com.example.springcrud.demo.Subject.Application.Dto.OutputStudentSubjectDto;
import com.example.springcrud.demo.Subject.Application.Dto.OutputSubjectDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectMapper {

    SubjectMapper INSTANCE = Mappers.getMapper(SubjectMapper.class);

    OutputSubjectDto subjectToOutputSubjectDto(Subject subject);

    Subject inputSubjectDtoToSubject(InputSubjectDto inputSubjectDto);

    List<OutputStudentSubjectDto> subjectListToOutputSubjectListDto(List<Subject> subjectList);
}
