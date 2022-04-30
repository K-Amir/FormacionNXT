package com.example.springcrud.demo.Person.Application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry
                        .addMapping("/person")
                        .allowedOrigins("*")
                        .allowedMethods("POST");
                registry
                        .addMapping("/person/all")
                        .allowedOrigins("*")
                        .allowedMethods("GET");
            }
        };
    }
}
