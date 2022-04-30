package com.springbootnxttest.springbootnxttest.Controllers;

import com.springbootnxttest.springbootnxttest.Models.City;
import com.springbootnxttest.springbootnxttest.Models.Person;
import com.springbootnxttest.springbootnxttest.Services.ModelService;
import com.springbootnxttest.springbootnxttest.Services.PersonBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController()
public class ExerciseOneControllerOne {

  @Autowired() private ModelService<Person> personService;
  @Autowired() private ModelService<ArrayList<City>> citiesService;




  @PostMapping("/person/setPerson")
  public void setPersona(@RequestBody Person person) {
      personService.setModel(person);
  }

  @PostMapping("/cities/addCity")
    public ResponseEntity<String> addCity(
            @RequestBody City city
  ){
    ArrayList<City> cities =  citiesService.getModel();
    if(cities == null){
      ArrayList<City> newCitiesList = new ArrayList<>();
      newCitiesList.add(city);
      citiesService.setModel(newCitiesList);
    } else {
      cities.add(city);
      citiesService.setModel(cities);
    }

    return new ResponseEntity<>("Success", HttpStatus.OK);
  }


}
