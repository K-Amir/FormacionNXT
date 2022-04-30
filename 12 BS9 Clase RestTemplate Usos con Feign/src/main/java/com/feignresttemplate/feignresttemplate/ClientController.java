package com.feignresttemplate.feignresttemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("client")
public class ClientController {
    @Autowired
    IFeignTest iFeignTest;

    @GetMapping("/rt/{id}")
    public ResponseEntity<?> getProfessorUsingRestTemplate(@PathVariable int id){
        ResponseEntity<ProfessorDto> output =
                new RestTemplate().getForEntity("http://localhost:8081/person/professor/"+id, ProfessorDto.class);

        return  ResponseEntity.ok().body(output.getBody());
    }

    @GetMapping("/feign/{id}")
    public ResponseEntity<?> getProfessorUsingFeign(@PathVariable int id){
        ResponseEntity<ProfessorDto> output = iFeignTest.getProfessorByIdFromOtherServer(id);
        return  ResponseEntity.ok().body(output.getBody());
    }
}
