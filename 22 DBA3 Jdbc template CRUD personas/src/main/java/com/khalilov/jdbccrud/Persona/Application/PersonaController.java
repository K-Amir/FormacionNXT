package com.khalilov.jdbccrud.Persona.Application;


import com.khalilov.jdbccrud.Persona.Domain.Persona;
import com.khalilov.jdbccrud.Persona.Domain.PersonaDAO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/personas")
public class PersonaController {
    private final PersonaDAO personaDAO;

    @GetMapping
    public ResponseEntity<?> findAllPersons(){
        return ResponseEntity.ok(personaDAO.findAllPersonas());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findPersonById(@PathVariable int id){
        return ResponseEntity.ok(personaDAO.findPersonById(id));
    }

    @PostMapping()
    public ResponseEntity<?> createPersona(@RequestBody Persona persona){
        personaDAO.createPersona(persona);
        return ResponseEntity.ok("Saved successfully");
    }

    @PutMapping()
    public ResponseEntity<?> updatePersona(@RequestBody Persona persona){
        personaDAO.updatePersona(persona);
        return ResponseEntity.ok("Updated successfully");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deletePersonaById(@PathVariable int id){
        personaDAO.deletePersonaById(id);
        return ResponseEntity.ok("Persona deleted successfully");
    }
}
