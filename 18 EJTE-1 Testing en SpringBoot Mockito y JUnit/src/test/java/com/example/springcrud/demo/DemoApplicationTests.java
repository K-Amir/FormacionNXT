package com.example.springcrud.demo;

import com.example.springcrud.demo.Person.Application.Dtos.personInDto;
import com.example.springcrud.demo.Person.Application.PersonController;
import com.example.springcrud.demo.Person.Domain.Person;
import com.example.springcrud.demo.Person.Domain.PersonRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


//@SpringBootTest
@WebMvcTest(PersonController.class)
@RunWith(SpringRunner.class)
class DemoApplicationTests {



    Person p = new Person(1, "skhal", "secret", "amir", "khalilov", "non@g", "no", "moscow", true, null, "no", null);

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonRepository personRepo;


    @Test
    void returnCorrectPersonId() throws Exception {
        when(personRepo.findOneById(1)).thenReturn(Optional.of(p));
        mockMvc.perform(get("/person/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.user", Matchers.is("skhal")))
                .andExpect(jsonPath("$.password", Matchers.is("secret")))
                .andExpect(jsonPath("$.name", Matchers.is("amir")))
                .andExpect(jsonPath("$.surname", Matchers.is("khalilov")))
                .andExpect(jsonPath("$.company_email", Matchers.is("non@g")))
                .andExpect(jsonPath("$.personal_email", Matchers.is("no")))
                .andExpect(jsonPath("$.active", Matchers.is(true)))
                .andExpect(jsonPath("$.city", Matchers.is("moscow")))
                .andExpect(jsonPath("$.image_url", Matchers.is("no")));
    }

    @Test
    void failedToFindPersonById() throws Exception {
        when(personRepo.findOneById(1)).thenReturn(Optional.empty());
        mockMvc.perform(get("/person/{id}", 1)
                        .contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isNotAcceptable());
    }

    @Test
    void findAllPersons() throws Exception {
        List<Person> persons = new ArrayList<>();
        persons.add(this.p);
        when(personRepo.findAll()).thenReturn(persons);

        mockMvc.perform(get("/person/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void findAllPersonsEmpty() throws Exception {
        when(personRepo.findAll()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/person/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void nameIsNotBetween6Or10() throws Exception {
        when(personRepo.savePersonToDb(p)).thenReturn(p);
        personInDto personIn = new personInDto(1, "skhal", "secret", "amir", "khalilov", "non@g", "no", "moscow", true, null, "no", null);

        performPostPerson(personIn);

        personIn.setName("thishasmorethanten");
        performPostPerson(personIn);

        personIn.setName(null);
        performPostPerson(personIn);

        personIn.setName("goodname");
        mockMvc.perform(post("/person")
                        .content(objectMapper.writeValueAsString(personIn))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testingDelete() throws Exception {

        doNothing().when(personRepo).deletePersonById(1);
        mockMvc.perform(delete("/person/{id}", 1)
                        .contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk());

        doThrow(new IllegalArgumentException()).when(personRepo).deletePersonById(5);
        mockMvc.perform(delete("/person/{id}", 5)
                        .contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testingFindPersonByName() throws Exception {
        List<Person> persons = new ArrayList<>();
        persons.add(p);

        when(personRepo.findByNameContaining("name")).thenReturn(persons);
        mockMvc.perform(get("/person?name=name")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(persons)));

        persons.clear();
        when(personRepo.findByNameContaining("name")).thenReturn(persons);
        mockMvc.perform(get("/person?name=name")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotAcceptable())
                .andExpect(content().string("Not found by that name"));


    }


    private void performPostPerson(personInDto personIn) throws Exception {
        mockMvc.perform(post("/person")
                        .content(objectMapper.writeValueAsString(personIn))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("name cannot be null, must be between 6 and 10 characters"));
    }


}
