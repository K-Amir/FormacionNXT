package com.nxtexercises.mappinglibraries.Utils;

import com.nxtexercises.mappinglibraries.Dtos.CarDto;
import com.nxtexercises.mappinglibraries.Dtos.ProductDto;
import com.nxtexercises.mappinglibraries.Dtos.ProductUSDto;
import com.nxtexercises.mappinglibraries.Dtos.UserDto;
import com.nxtexercises.mappinglibraries.Models.Car;
import com.nxtexercises.mappinglibraries.Models.User;
import jdk.jfr.Name;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MapperUtil {

    MapperUtil INSTANCE
            = Mappers.getMapper(MapperUtil.class);

    @Named("euroToDollar")
    static double euroToDollar(double priceEur){
        return priceEur * 1.10;
    }

    @Mapping(
            source = "priceEUR",
            target = "priceUSD",
            qualifiedByName = "euroToDollar"
    )
    ProductUSDto productEurToUsdDto(ProductDto productDto);

    @Mapping(source = "ruedas",target = "wheels")
    CarDto carToCarDto(Car car);

    UserDto userToUserDto(User user);





}
