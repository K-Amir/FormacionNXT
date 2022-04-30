package com.springbootnxttest.springbootnxttest.Services;

import com.springbootnxttest.springbootnxttest.Models.Person;

public class BeanOneService implements PersonBean {
    @Override
    public Person getPerson() {
        return new Person("bean1",1,"bean1",1);
    }
}
