package com.microshop.order.events;

import com.microshop.common.model.BaseModel;
import com.microshop.common.constants.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreatedEvent extends BaseModel {
    private Long customerId;
    private Double totalAmount;
    private OrderStatus status;
}
