package com.springbootnxttest.springbootnxttest;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@PropertySource("classpath:miconfiguracion.properties")
@Data
public class Configuracion {

    @Value("${valor1}")
    private String valor1;


    @Value("${valor2}")
    private String valor2;
}
