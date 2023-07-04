package com.spiderweb.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

    @KafkaListener(topics = "spiderweb2.0", groupId = "hello")
    void Listener( String data){
        System.out.println("Listener received : " + data + " hello");
    }
}
