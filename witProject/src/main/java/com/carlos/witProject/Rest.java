package com.carlos.witProject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Rest {

    private static final Logger Logger = LoggerFactory.getLogger(Rest.class);
    private KafkaTemplate<String, String> kafkaTemplate;

    public Rest(KafkaTemplate<String, String> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message){
        kafkaTemplate.send("calculator", message);
    }
}
