package com.carlos.witProject.MDCProp;
import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.MDC;

import java.util.Map;

public class MDCProducerInterceptor implements ProducerInterceptor<String, String> {

    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
        // Capture the MDC context (e.g., X-Request-ID)
        String requestId = MDC.get("X-Request-ID");

        // If the MDC context is present, inject it as a header to the Kafka message
        if (requestId != null) {
            record.headers().add("X-Request-ID", requestId.getBytes());
        }

        return record;
    }

    @Override
    public void configure(Map<String, ?> configs) {}

    @Override
    public void close() {}

    @Override
    public void onAcknowledgement(org.apache.kafka.clients.producer.RecordMetadata metadata, Exception exception) {}
}
