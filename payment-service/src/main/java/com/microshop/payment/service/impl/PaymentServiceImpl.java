package com.microshop.payment.service.impl;

import com.microshop.payment.entity.Payment;
import com.microshop.payment.mapper.PaymentMapper;
import com.microshop.payment.model.PaymentModel;
import com.microshop.payment.repository.PaymentRepository;
import com.microshop.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    @Override
    public List<PaymentModel> findAllPayments() {
        return paymentMapper.convertToModelList(paymentRepository.findAll());
    }

    @Override
    public Optional<PaymentModel> findPaymentById(Long id) {
        return paymentRepository.findById(id).map(paymentMapper::convertToModel);
    }

    @Override
    public PaymentModel savePayment(PaymentModel paymentModel) {
        Payment payment = paymentMapper.convertToEntity(paymentModel);
        return paymentMapper.convertToModel(paymentRepository.save(payment));
    }

    @Override
    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }
}
