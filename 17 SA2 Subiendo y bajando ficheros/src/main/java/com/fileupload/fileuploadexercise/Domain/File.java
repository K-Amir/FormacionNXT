package com.fileupload.fileuploadexercise.Domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.tomcat.jni.Local;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String filename;

    private LocalDate uploadDate = LocalDate.now();

    private String metadata;

    public File(String filename, String metadata){
        this.filename = filename;
        this.metadata = metadata;
    }



}
