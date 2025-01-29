package com.carlos.witProject;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {
    private static final Logger Logger = LoggerFactory.getLogger(Consumer.class);

    @KafkaListener(topics = "calculator", groupId = "witGroup")
    public void consume(String message){
        Logger.info(String.format("Message received -> %s", message));
    }
}
