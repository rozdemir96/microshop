package com.microshop.payment.entity;

import com.microshop.common.entity.BaseEntity;
import com.microshop.payment.constants.PaymentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "payments")
@NoArgsConstructor
@AllArgsConstructor
public class Payment extends BaseEntity {
    private Long orderId;
    private Double amount;
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
}
