package com.springbootnxttest.springbootnxttest.Services;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component()
@Profile("perfil1")
@Data
public class Perfil1 implements Perfiles{
    @Value("${perfil}")
    private String profile;

    @Override
    public void miFuncion() {
    System.out.println("Perfil1");
    }
}
