package com.carlos.witProject;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {


    private static final Logger Logger = LoggerFactory.getLogger(Consumer.class);

    //logs messages sent to topic "calcutalor" by producer with request id
    @KafkaListener(topics = "calculator", groupId = "witGroup")
    public void consume(ConsumerRecord<String, String> message){

        byte[] requestId = message.headers().lastHeader("Request-ID").value();
        if (requestId != null) {
            MDC.put("Request-ID", new String(requestId));
        }

        Logger.info(String.format("Message received -> %s", message.value()));

        MDC.clear();
    }
}
