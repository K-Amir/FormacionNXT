package com.example.springcrud.demo.Person.Domain;

import com.example.springcrud.demo.Person.Application.Dtos.personInDto;
import com.example.springcrud.demo.Person.Application.Dtos.personOutDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    personOutDto personToPersonOutDto(Person person);

    Person personInDtoToPerson(personInDto person);

    List<personOutDto> personListToPersonOutDto(List<Person> persons);



}
