package com.mongodbcrud.mongodbcrud.Domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;

@Document(collection = "person")
@Data
public class Person {
    @MongoId
    @Id
    ObjectId _id;

    String user;

    String password;

    String name;

    String surname;

    String company_email;

    String personal_email;

    String city;

    Boolean active;

    Date created_date = Timestamp.from(Instant.now());

    String image_url;

    Date termination_date;
}
