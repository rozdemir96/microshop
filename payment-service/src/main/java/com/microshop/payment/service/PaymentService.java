package com.microshop.payment.service;

import com.microshop.payment.model.PaymentModel;

import java.util.List;
import java.util.Optional;

public interface PaymentService {
    List<PaymentModel> findAllPayments();
    Optional<PaymentModel> findPaymentById(Long id);
    PaymentModel savePayment(PaymentModel paymentModel);
    void deletePayment(Long id);
}
