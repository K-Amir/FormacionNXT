package com.fileupload.fileuploadexercise.Infrastructure.Jpa;


import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class StorageProperties {
    private String location = "upload";


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) throws IOException {
        Path pathToChange = Paths.get(location).toAbsolutePath();
        Files.createDirectories(pathToChange);
        this.location = location;
    }
}
