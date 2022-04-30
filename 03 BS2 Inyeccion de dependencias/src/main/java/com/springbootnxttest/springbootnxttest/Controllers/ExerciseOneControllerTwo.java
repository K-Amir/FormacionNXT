package com.springbootnxttest.springbootnxttest.Controllers;

import com.springbootnxttest.springbootnxttest.Models.City;
import com.springbootnxttest.springbootnxttest.Models.Person;
import com.springbootnxttest.springbootnxttest.Services.ModelService;
import com.springbootnxttest.springbootnxttest.Services.PersonBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Objects;

@RestController()
public class ExerciseOneControllerTwo {
  @Autowired() private ModelService<Person> personService;

  @Qualifier("citiesService")
  @Autowired() private ModelService<ArrayList<City>> citiesService;

  @Qualifier("getBeanOne")
  @Autowired() private PersonBean beanServiceOne;

  @Qualifier("getBeanTwo")
  @Autowired() private PersonBean beanServiceTwo;

  @Qualifier("getBeanThree")
  @Autowired() private PersonBean beanServiceThree;

  @GetMapping("/beans/{bean}")
  public ResponseEntity<Person> getBean(
          @PathVariable String bean
  ){
      if(Objects.equals(bean, "bean1")){
        return  new ResponseEntity<>(beanServiceOne.getPerson(), HttpStatus.OK);
      }
    if(Objects.equals(bean, "bean2")){
      return  new ResponseEntity<>(beanServiceTwo.getPerson(), HttpStatus.OK);
    }
    if(Objects.equals(bean, "bean3")){
      return  new ResponseEntity<>(beanServiceThree.getPerson(), HttpStatus.OK);
    }

    return  new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

  }

  @GetMapping("/person/getPerson")
  public ResponseEntity<Person> getPerson() {
    Person p = personService.getModel();
    if(p==null){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    p.setAge(p.getAge() * 2);
    return new ResponseEntity<>(p, HttpStatus.OK);
  }

  @GetMapping("/cities/getCities")
  public ResponseEntity<ArrayList<City>> getCities(){
    return new ResponseEntity<>(citiesService.getModel(), HttpStatus.OK);
  }
}
