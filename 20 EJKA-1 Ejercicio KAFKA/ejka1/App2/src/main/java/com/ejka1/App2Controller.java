package com.ejka1;


import com.ejka1.shared.Message;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("app2")
public record App2Controller(KafkaTemplate<String, Message> kafkaTemplate) {

    @PostMapping()
    public void sendMessageToTopic(@RequestBody Message message){
        kafkaTemplate.send("messages",message);
    }




}
