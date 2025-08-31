package com.microshop.order.service.impl;

import com.microshop.order.entity.Order;
import com.microshop.order.mapper.OrderMapper;
import com.microshop.order.model.OrderModel;
import com.microshop.order.repository.OrderRepository;
import com.microshop.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

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
        return orderMapper.convertToModel(orderRepository.save(order));
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
