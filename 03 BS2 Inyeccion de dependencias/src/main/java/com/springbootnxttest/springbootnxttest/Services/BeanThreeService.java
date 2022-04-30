package com.springbootnxttest.springbootnxttest.Services;

import com.springbootnxttest.springbootnxttest.Models.Person;

public class BeanThreeService implements PersonBean{
    @Override
    public Person getPerson() {
        return new Person("bean3",3,"bean3");
    }
}
