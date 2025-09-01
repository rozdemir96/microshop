package com.microshop.order.service.impl;

import com.microshop.order.entity.Order;
import com.microshop.order.events.OrderCreatedEvent;
import com.microshop.order.mapper.OrderMapper;
import com.microshop.order.model.OrderModel;
import com.microshop.order.repository.OrderRepository;
import com.microshop.order.service.KafkaProducerService;
import com.microshop.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final KafkaProducerService kafkaProducerService;

    @Value("${spring.kafka.topics.order-created}")
    private String orderCreatedTopic;

    @Override
    public List<OrderModel> findAllOrders() {
        return orderMapper.convertToModelList(orderRepository.findAll());
    }

    @Override
    public Optional<OrderModel> findOrderById(Long id) {
        return orderRepository.findById(id).map(orderMapper::convertToModel);
    }

    @Override
    public OrderModel saveOrder(OrderModel orderModel) {
        Order order = orderMapper.convertToEntity(orderModel);
        Order savedOrder = orderRepository.save(order);
        OrderCreatedEvent event = new OrderCreatedEvent(
                savedOrder.getCustomerId(), savedOrder.getTotalAmount(), savedOrder.getStatus()
        );
        event.setId(savedOrder.getId());
        event.setCreatedDate(savedOrder.getCreatedDate());
        event.setCreatedUserId(savedOrder.getCreatedUserId());

        kafkaProducerService.sendOrderCreatedEvent(orderCreatedTopic, event);
        return orderMapper.convertToModel(savedOrder);
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
