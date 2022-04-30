package com.nxtexercises.mappinglibraries;

import com.nxtexercises.mappinglibraries.Dtos.CarDto;
import com.nxtexercises.mappinglibraries.Dtos.ProductDto;
import com.nxtexercises.mappinglibraries.Dtos.ProductUSDto;
import com.nxtexercises.mappinglibraries.Dtos.UserDto;
import com.nxtexercises.mappinglibraries.Models.Car;
import com.nxtexercises.mappinglibraries.Models.CarType;
import com.nxtexercises.mappinglibraries.Models.User;
import com.nxtexercises.mappinglibraries.Utils.MapperUtil;
import org.modelmapper.ModelMapper;

public class Main {

  public static void main(String[] args) {
      Car impreza = new Car("Subaru","Impreza", CarType.EXTRASPORT,5);
      User user = new User("example@gmx.es","jhon","secret","private address 54");

      // Using MapStruct
      CarDto carDto = MapperUtil.INSTANCE.carToCarDto(impreza);
      UserDto userDto = MapperUtil.INSTANCE.userToUserDto(user);

      //Using ModelMapper
      ModelMapper modelMapper = new ModelMapper();
      UserDto userDtoModelMapper = modelMapper.map(user, UserDto.class);

      //Using methods
      ProductDto car =
              new ProductDto("Subaru Impreza",23000);
      ProductUSDto carUSD =
              MapperUtil.INSTANCE.productEurToUsdDto(car);

      System.out.println(car);
      System.out.println(carUSD);

      System.out.println(userDto);
      System.out.println(userDtoModelMapper);
  }


}
