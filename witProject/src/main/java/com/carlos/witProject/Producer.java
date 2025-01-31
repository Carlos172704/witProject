package com.carlos.witProject;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class Producer {

    private static final Logger Logger = LoggerFactory.getLogger(Producer.class);
    private final KafkaTemplate<String, String> kafkaTemplate;

    public Producer(KafkaTemplate<String, String> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    //send message to kafka with mdc and request id
    public void sendMessage(String data){
        MDC.put("Request-ID", UUID.randomUUID().toString());

        ProducerRecord<String, String> record = new ProducerRecord<>("calculator", data);
        record.headers().add("Request-ID", MDC.get("Request-ID").getBytes());
        System.out.println(MDC.get("Request-ID"));

        Logger.info(String.format("Message sent -> %s", data));
        kafkaTemplate.send(record);

        MDC.clear();
    }
}
