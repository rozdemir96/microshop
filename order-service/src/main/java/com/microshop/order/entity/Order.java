package com.microshop.order.entity;

import com.microshop.common.entity.BaseEntity;
import com.microshop.order.constants.OrderStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
public class Order extends BaseEntity {
    private Long customerId;
    private Double totalAmount;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}
