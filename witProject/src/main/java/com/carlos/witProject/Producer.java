package com.carlos.witProject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {

    private static final Logger Logger = LoggerFactory.getLogger(Producer.class);
    private final KafkaTemplate<String, String> kafkaTemplate;

    public Producer(KafkaTemplate<String, String> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String data){

        //Message<Calc> message= MessageBuilder.withPayload(data).setHeader(KafkaHeaders.TOPIC,"calculator").build();
        Logger.info(String.format("Message sent -> %s", data));
        kafkaTemplate.send("calculator", data);
    }
}
