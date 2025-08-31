package com.microshop.order.mapper;

import com.microshop.order.entity.Order;
import com.microshop.order.model.OrderModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderModel convertToModel(Order order);
    Order convertToEntity(OrderModel orderModel);
    List<OrderModel> convertToModelList(List<Order> orders);
}
