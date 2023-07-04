package com.spiderweb.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/kafka")
public class KafkaController {

    private final KafkaTemplate<String, String> kafkaTemplate;


    public KafkaController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping()
    public void sendMessage(@RequestBody MessageRequest messageRequest) {
        kafkaTemplate.send("spiderweb2.0", messageRequest.message());
    }
}
