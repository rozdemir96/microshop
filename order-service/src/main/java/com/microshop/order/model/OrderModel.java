package com.microshop.order.model;

import com.microshop.common.model.BaseModel;
import com.microshop.order.constants.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class OrderModel extends BaseModel {
    private Long customerId;
    private Double totalAmount;
    private OrderStatus status;
}
