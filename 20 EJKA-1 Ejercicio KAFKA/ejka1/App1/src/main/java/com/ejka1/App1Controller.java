package com.ejka1;




import com.ejka1.shared.Message;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("app1")
public record App1Controller(KafkaTemplate<String,Message> kafkaTemplate) {

    @PostMapping()
    public void sendMessageToKafka(@RequestBody Message message){
        kafkaTemplate.send("messages",message);
    }


}
