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

    public void sendMessage(String data){
        MDC.put("X-Request-ID", UUID.randomUUID().toString());

        ProducerRecord<String, String> record = new ProducerRecord<>("calculator", data);
        record.headers().add("X-Request-ID", MDC.get("X-Request-ID").getBytes());
        System.out.println(MDC.get("X-Request-ID"));

        Logger.info(String.format("Message sent -> %s", data));
        kafkaTemplate.send(record);

        MDC.clear();
    }
}
