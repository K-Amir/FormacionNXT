package com.mongodbcrud.mongodbcrud.Application;

import com.mongodbcrud.mongodbcrud.Application.Dtos.PersonInDto;
import com.mongodbcrud.mongodbcrud.Application.Dtos.PersonOutDto;
import com.mongodbcrud.mongodbcrud.Domain.Person;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface PersonMapper {
    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    Person personInDtoToPerson(PersonInDto personInDto);

    PersonOutDto personToPersonOutDto(Person person);


}
