package com.springbootnxttest.springbootnxttest;

import com.springbootnxttest.springbootnxttest.Models.City;
import com.springbootnxttest.springbootnxttest.Services.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;


@Configuration
public class Application {

    @Bean
    public ModelService<ArrayList<City>> citiesService(){
        return new ModelService<>();
    }

    @Bean
    public PersonBean getBeanOne(){
        return new BeanOneService();
    }
    @Bean
    public PersonBean getBeanTwo(){
        return new BeanTwoService();
    }
    @Bean
    public PersonBean getBeanThree(){
        return new BeanThreeService();
    }


}
