package com.microshop.payment.model;

import com.microshop.common.model.BaseModel;
import com.microshop.common.constants.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class PaymentModel extends BaseModel {
    private Long orderId;
    private Double amount;
    private PaymentStatus status;
}
