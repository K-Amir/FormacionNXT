package com.khalilov.jdbccrud.Persona.Domain;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;


@Service
public class PersonMapper implements RowMapper<Persona> {
    @Override
    public Persona mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Persona(rs.getInt("id"),
                rs.getString("user"),
                rs.getString("password"),
                rs.getString("name"),
                rs.getString("surname"),
                rs.getString("company_email"),
                rs.getString("personal_email"),
                rs.getString("city"),
                rs.getBoolean("active"),
                rs.getDate("created_date"),
                rs.getString("image_url"),
                rs.getDate("termination_date"));
    }
}
