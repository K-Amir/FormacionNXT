package com.springbootnxttest.springbootnxttest.Controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PropertiesController {

    @Value("${VAR1}")
    private String VAR1;

    @Value("${My.VAR2}")
    private int MyVAR2;

    @Value("${VAR3:Var 3 no tiene valor}")
    private String VAR3;

    @GetMapping("/valores")
    public String getValues()
    {
        return "valor de var1 es: " + VAR1 + " valor de my.var2 es " + MyVAR2;
    }

    @GetMapping("/var3")
    public String getVarThree()
    {
        return "valor de var3 es: " + VAR3;
    }
}
