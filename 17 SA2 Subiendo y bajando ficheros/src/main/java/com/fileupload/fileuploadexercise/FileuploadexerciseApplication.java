package com.fileupload.fileuploadexercise;

import com.fileupload.fileuploadexercise.Infrastructure.Jpa.StorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FileuploadexerciseApplication {


	public static void main(String[] args) {
		SpringApplication.run(FileuploadexerciseApplication.class, args);

	}



	@Bean
	CommandLineRunner init(StorageProperties storageProperties){
		return (args) -> {
			if(args[0] != null){
				storageProperties.setLocation(args[0]);
			}
		};
	}

}
