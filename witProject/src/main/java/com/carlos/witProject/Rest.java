package com.carlos.witProject;

import com.carlos.witProject.payload.Calc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class Rest {

    private static final Logger Logger = LoggerFactory.getLogger(Rest.class);
    private final KafkaTemplate<String, String> kafkaTemplate;

    public Rest(KafkaTemplate<String, String> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String data){

        //Message<Calc> message= MessageBuilder.withPayload(data).setHeader(KafkaHeaders.TOPIC,"calculator").build();
        Logger.info(String.format("Message sent -> %s", data));
        kafkaTemplate.send("calculator", data);
    }
}
