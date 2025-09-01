package com.microshop.common.events;

import com.microshop.common.constants.PaymentStatus;
import com.microshop.common.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class PaymentProcessedEvent extends BaseModel {
    private Long orderId;
    private Double amount;
    private PaymentStatus status;
}
