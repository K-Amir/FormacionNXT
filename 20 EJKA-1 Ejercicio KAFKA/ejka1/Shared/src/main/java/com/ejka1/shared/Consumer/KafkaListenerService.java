package com.ejka1.shared.Consumer;


import com.ejka1.shared.Message;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaListenerService {
    @KafkaListener(topics = "messages", groupId = "messagesGroupId")
    public void listenKafkaMessages(Message message){
        System.out.println("Messages from kafka: " + message);
    }
}
