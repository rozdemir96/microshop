package com.microshop.order.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microshop.common.constants.OrderStatus;
import com.microshop.order.entity.Order;
import com.microshop.order.repository.OrderRepository;
import com.microshop.common.events.PaymentFailedEvent;
import com.microshop.common.events.PaymentProcessedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumerService {

    private final OrderRepository orderRepository;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "${spring.kafka.topics.payment-processed}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumePaymentProcessedEvent(PaymentProcessedEvent event) {
        log.info("PaymentProcessedEvent received: {}", event);

        Optional<Order> orderOptional = orderRepository.findById(event.getOrderId());
        orderOptional.ifPresent(order -> {
            order.setStatus(OrderStatus.DELIVERED);
            orderRepository.save(order);
            log.info("Order {} status updated to DELIVERED", order.getId());
        });
    }

    @KafkaListener(topics = "${spring.kafka.topics.payment-failed}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumePaymentFailedEvent(PaymentFailedEvent event) {
        Optional<Order> orderOptional = orderRepository.findById(event.getOrderId());
        orderOptional.ifPresent(order -> {
            order.setStatus(OrderStatus.CANCELLED);
            orderRepository.save(order);
            log.info("Order {} status updated to CANCELLED. Reason: {}", order.getId(), event.getReason());
        });
    }
}
