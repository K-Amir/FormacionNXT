package com.khalilov.jdbccrud.Persona.Domain;


import java.util.List;
import java.util.Optional;

public interface PersonaDAO {
    Optional<Persona> findPersonById(Integer id);

    List<Persona> findAllPersonas();

    void deletePersonaById(Integer id);

    void updatePersona(Persona persona);

    void createPersona(Persona persona);
}
