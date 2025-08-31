package com.microshop.payment.mapper;

import com.microshop.payment.entity.Payment;
import com.microshop.payment.model.PaymentModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PaymentMapper {
    PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);

    PaymentModel convertToModel(Payment payment);
    Payment convertToEntity(PaymentModel paymentModel);
    List<PaymentModel> convertToModelList(List<Payment> payments);
}
