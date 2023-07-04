package com.spiderweb.kafka;

import com.spiderweb.kafka.model.Message;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class KafkaListeners {

//    Facing Issue to deserialize the customObject
    @KafkaListener(topics = "spiderweb2.0", groupId = "hello")
    void Listener( String data){
        System.out.println("Listener received : " + data);
    }
}
