package com.feignresttemplate.feignresttemplate;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="TestingFeign",url="http://localhost:8081/")
public interface IFeignTest {

    @GetMapping("person/professor/{id}")
    ResponseEntity<ProfessorDto> getProfessorByIdFromOtherServer(@PathVariable int id);
}
