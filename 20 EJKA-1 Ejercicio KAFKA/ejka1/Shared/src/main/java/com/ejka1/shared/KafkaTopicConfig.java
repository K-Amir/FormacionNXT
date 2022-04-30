package com.ejka1.shared;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {



    private String boostrapAddress = "localhost:9092";

    @Bean
    public NewTopic messagesTopic(){
        return TopicBuilder.name("messages").build();
    }

}
