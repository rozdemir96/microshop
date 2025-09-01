package com.microshop.payment.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microshop.common.events.PaymentFailedEvent;
import com.microshop.common.events.PaymentProcessedEvent;
import com.microshop.common.constants.PaymentStatus;
import com.microshop.order.events.OrderCreatedEvent;
import com.microshop.payment.entity.Payment;
import com.microshop.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumerService {

    private final PaymentRepository paymentRepository;
    private final KafkaProducerService kafkaProducerService;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "${spring.kafka.topics.order-created}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeOrderCreatedEvent(OrderCreatedEvent event) {
        log.info("OrderCreatedEvent received: {}", event);

        // Simulate payment processing
        Payment payment = new Payment();
        payment.setOrderId(event.getId());
        payment.setAmount(event.getTotalAmount());
        payment.setStatus(PaymentStatus.PENDING);
        payment.setCreatedDate(Instant.now());
        payment.setCreatedUserId(event.getCreatedUserId());
        // You might want to set updated/deleted fields as null or initial values
        paymentRepository.save(payment);

        // Simulate payment success or failure
        if (Math.random() > 0.5) { // 50% chance of success
            payment.setStatus(PaymentStatus.COMPLETED);
            paymentRepository.save(payment);
            PaymentProcessedEvent processedEvent = new PaymentProcessedEvent(
                    payment.getOrderId(), payment.getAmount(), payment.getStatus()
            );
            processedEvent.setId(payment.getId());
            processedEvent.setCreatedDate(payment.getCreatedDate());
            processedEvent.setCreatedUserId(payment.getCreatedUserId());
            kafkaProducerService.sendPaymentProcessedEvent("payment-processed-topic", processedEvent);
        } else {
            payment.setStatus(PaymentStatus.FAILED);
            paymentRepository.save(payment);
            PaymentFailedEvent failedEvent = new PaymentFailedEvent(
                    payment.getOrderId(), payment.getAmount(), payment.getStatus(), "Insufficient funds"
            );
            failedEvent.setId(payment.getId());
            failedEvent.setCreatedDate(payment.getCreatedDate());
            failedEvent.setCreatedUserId(payment.getCreatedUserId());
            kafkaProducerService.sendPaymentFailedEvent("payment-failed-topic", failedEvent);
        }

    }
}
