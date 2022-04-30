package com.example.springcrud.demo.Person.Infrastructure;

import com.example.springcrud.demo.Person.Domain.Person;
import com.example.springcrud.demo.Person.Domain.PersonRepository;
import com.example.springcrud.demo.Person.Infrastructure.Jpa.ImportedPersonJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

// The repository itself working
@Service
public class PersonJpaRepository implements PersonRepository {

    private final ImportedPersonJpaRepository impl;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public PersonJpaRepository(ImportedPersonJpaRepository impl) {
        this.impl = impl;

    }

    @Override
    public List<Person> findByName(String name) {
        return impl.findByName(name);
    }

    @Override
    public List<Person> getData(HashMap<String, Object> conditions) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Person> query = cb.createQuery(Person.class);
        Root<Person> root = query.from(Person.class);

        List<Predicate> predicates = new ArrayList<>();

        conditions.forEach((field, value) -> {
            switch (field) {
                case "user":
                    predicates.add(cb.equal(root.get(field), (String) value));
                    break;
                case "name":
                    predicates.add(cb.equal(root.get(field), (String) value));
                    break;
                case "surname":
                    predicates.add(cb.equal(root.get(field), (String) value));
                    break;
                case "created_date":
                    String dateCondition = (String) conditions.get("dateCondition");
                    switch (dateCondition) {
                        case "greater":
                            try {
                                predicates.add(cb.greaterThan(root.get(field),  (Date)new SimpleDateFormat("yyyyMMdd").parse((String) value)));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            break;
                        case "less":
                            try {
                                predicates.add(cb.lessThan(root.get(field), (Date)new SimpleDateFormat("yyyyMMdd").parse((String) value)));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            break;
                        case "equal":
                            try {
                                predicates.add(cb.equal(root.get(field),  (Date)new SimpleDateFormat("yyyyMMdd").parse((String) value)));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            break;
                    }
                    break;
                case "orderBy":
                    query.orderBy(cb.desc(root.get((String) value)));
                    break;
            }
        });

        query.select(root).where(predicates.toArray(new Predicate[predicates.size()]));

        int pageSize = 10;
        if(conditions.get("pageSize") != null){
            pageSize = Integer.parseInt((String)conditions.get("pageSize"));
        }

        return entityManager
                .createQuery(query)
                .setMaxResults(pageSize)
                .setFirstResult(Integer.parseInt((String) conditions.get("pageIndex")))
                .getResultList();
    }

    @Override
    public List<Person> findByNameContaining(String name) {
        return impl.findByNameContaining(name);
    }

    @Override
    public Optional<Person> findOneById(Integer id) {
        return impl.findById(id);
    }

    @Override
    public List<Person> findAll() {
        return impl.findAll();
    }

    @Override
    public Person savePersonToDb(Person person) {
        return impl.saveAndFlush(person);
    }

    @Override
    public Person findByEmail(String email) {
        return impl.findPersonByCompany_email(email);
    }

    @Override
    public void deletePersonById(Integer id) {
        impl.deleteById(id);
    }
}
