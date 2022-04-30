package com.springbootnxttest.springbootnxttest.Services;

import com.springbootnxttest.springbootnxttest.Models.Person;

public class BeanTwoService implements  PersonBean{
    @Override
    public Person getPerson() {
        return new Person("bean2",2,"bean2",4);
    }
}
