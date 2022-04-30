package com.springbootnxttest.springbootnxttest.Controllers;

import com.springbootnxttest.springbootnxttest.Configuracion;
import com.springbootnxttest.springbootnxttest.Services.Perfiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class PropExerciseController {
    @Autowired
    private Configuracion config;

    @Autowired
    private Perfiles profile;

    @Value("${spring.profiles.active}")
    private String profileValue;

    @Value("${url}")
    private String url;

   @Value("${password}")
    private String password;



    @GetMapping("/parametros")
    public String getParams()
    {
        return "Los valores son Url:" + url + ", Password:" + password;
    }

    @GetMapping("/miconfiguracion")
    public String getMiConfiguracion()
    {
        return "Valor1: " + config.getValor1()  + "Valor2: " + config.getValor2();
    }

    @GetMapping("/perfil")
    public String getProfile(){
        profile.miFuncion();
        return profileValue;
    }
}
