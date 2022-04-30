package com.feignresttemplate.feignresttemplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FeignresttemplateApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeignresttemplateApplication.class, args);
	}

}
