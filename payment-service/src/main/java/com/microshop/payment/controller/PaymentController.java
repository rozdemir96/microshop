package com.microshop.payment.controller;

import com.microshop.payment.model.PaymentModel;
import com.microshop.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping
    public ResponseEntity<List<PaymentModel>> getAllPayments() {
        return ResponseEntity.ok(paymentService.findAllPayments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentModel> getPaymentById(@PathVariable Long id) {
        return paymentService.findPaymentById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PaymentModel> createPayment(@RequestBody PaymentModel paymentModel) {
        return ResponseEntity.ok(paymentService.savePayment(paymentModel));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentModel> updatePayment(@PathVariable Long id, @RequestBody PaymentModel paymentModel) {
        return paymentService.findPaymentById(id)
                .map(existingPaymentModel -> {
                    paymentModel.setId(id);
                    return ResponseEntity.ok(paymentService.savePayment(paymentModel));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }
}
