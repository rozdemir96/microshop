package com.microshop.payment.service;

import com.microshop.common.events.PaymentFailedEvent;
import com.microshop.common.events.PaymentProcessedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendPaymentProcessedEvent(String topic, PaymentProcessedEvent event) {
        kafkaTemplate.send(topic, event);
    }

    public void sendPaymentFailedEvent(String topic, PaymentFailedEvent event) {
        kafkaTemplate.send(topic, event);
    }
}
