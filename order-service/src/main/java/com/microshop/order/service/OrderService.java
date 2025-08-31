package com.microshop.order.service;

import com.microshop.order.model.OrderModel;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<OrderModel> findAllOrders();
    Optional<OrderModel> findOrderById(Long id);
    OrderModel saveOrder(OrderModel orderModel);
    void deleteOrder(Long id);
}
