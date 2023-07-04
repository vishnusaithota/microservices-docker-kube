package com.spiderweb.kafka.controller;

import com.spiderweb.kafka.model.Message;
import com.spiderweb.kafka.model.MessageRequest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("api/v1/kafka")
public class KafkaController {

    private final KafkaTemplate<String, Message> kafkaTemplate;


    public KafkaController(KafkaTemplate<String, Message> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping()
    public void sendMessage(@RequestBody MessageRequest messageRequest) {
        Message messageObject = new Message(messageRequest.message(), LocalDateTime.now());
        kafkaTemplate.send("spiderweb2.0", messageObject );
    }
}
