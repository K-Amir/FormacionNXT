package com.springbootnxttest.springbootnxttest;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

import javax.annotation.PostConstruct;
import java.util.Collections;


@SpringBootApplication
public class SpringbootnxttestApplication {


  public static void main(String[] args) {
    SpringApplication app = new SpringApplication(SpringbootnxttestApplication.class);
    app.setDefaultProperties(
            Collections
            .singletonMap("server.port","8083")
    );
    app.run(args);
  }

  // Primero se ejecuta este métdo que es cuando
  // se instancia nuestro SpringbootnxttestApplication
  // Se muestra esta funcion primero con PostConstruct indicamos
  // que se ejecute nada más se instancie.
  @PostConstruct
  public void afterCreate(){
    System.out.println("Hola desde clase inicial");
  }



  // Y tras la instanciación se ejecutan en orden los siguientes CommandLineRunner
  @Bean
  CommandLineRunner secondMsg(){
    return p ->
    {
      System.out.println("Hola desde la segunda clase");
    };
  }

  @Bean
  CommandLineRunner thirdMsg(ApplicationArguments args) throws  Exception{
    return p -> {
      System.out.println("Valores pasados como parametros al ejectuar la máquina: ");
     args.getNonOptionArgs().forEach(System.out::println);

    };
  }
}
