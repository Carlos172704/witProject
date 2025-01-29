package com.carlos.witProject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Calculator {
    private static final Logger Logger = LoggerFactory.getLogger(Calculator.class);

    @KafkaListener(topics = "calculator", groupId = "witGroup")
    public void consume(String message){
        Logger.info(String.format("Masage received -> %s", message));
    }
}
