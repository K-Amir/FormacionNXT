package com.khalilov.jdbccrud.Persona.Infrastructure;

import com.khalilov.jdbccrud.Persona.Domain.PersonMapper;
import com.khalilov.jdbccrud.Persona.Domain.Persona;
import com.khalilov.jdbccrud.Persona.Domain.PersonaDAO;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
@AllArgsConstructor
public class PersonaDAOImpl implements PersonaDAO {

    private final JdbcTemplate jdbcTemplate;


    @Override
    public Optional<Persona> findPersonById(Integer id) {
        return jdbcTemplate.query(
                "SELECT * FROM personas WHERE id = ?",
                new PersonMapper(),
                id
        ).stream().findFirst();
    }

    @Override
    public List<Persona> findAllPersonas() {
        return jdbcTemplate.query("SELECT * FROM personas", new PersonMapper());
    }

    @Override
    public void deletePersonaById(Integer id) {
        jdbcTemplate.update("DELETE FROM  personas WHERE id = ?", id);
    }

    @Override
    public void updatePersona(Persona persona) {
        jdbcTemplate.update("UPDATE personas SET user = ? , password = ?, name = ?,  surname = ?,  company_email = ?,  personal_email = ?,   city = ?,   active = ?,  created_date = ?,  image_url = ?,  termination_date = ? WHERE id = ?",
                persona.getUser(),
                persona.getPassword(),
                persona.getName(),
                persona.getSurname(),
                persona.getCompany_email(),
                persona.getPersonal_email(),
                persona.getCity(),
                persona.getActive(),
                persona.getCreated_date(),
                persona.getImage_url(),
                persona.getTermination_date(),
                persona.getId()
        );
    }

    @Override
    public void createPersona(Persona persona) {
        jdbcTemplate.update(
                "insert into personas(user,password,name,surname,company_email, personal_email, city,active,created_date, image_url,termination_date) values(?,?,?,?,?,?,?,?,?,?,?)",
                persona.getUser(),
                persona.getPassword(),
                persona.getName(),
                persona.getSurname(),
                persona.getCompany_email(),
                persona.getPersonal_email(),
                persona.getCity(),
                persona.getActive(),
                persona.getCreated_date(),
                persona.getImage_url(),
                persona.getTermination_date()
        );
    }
}
