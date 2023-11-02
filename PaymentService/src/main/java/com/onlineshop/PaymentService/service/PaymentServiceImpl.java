package com.onlineshop.PaymentService.service;

import com.onlineshop.PaymentService.entity.TransactionDetails;
import com.onlineshop.PaymentService.model.PaymentRequest;
import com.onlineshop.PaymentService.repository.TransactionDetailsRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Log4j2
@Service
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    TransactionDetailsRepository repository;

    @Override
    public String doPayment(PaymentRequest paymentRequest) {
        log.info("Recording Payment Details: {}", paymentRequest);

        TransactionDetails transactionDetails =
                TransactionDetails.builder()
                        .id(UUID.randomUUID().toString())
                        .paymentDate(Instant.now())
                        .paymentMode(paymentRequest.getPaymentMode().name())
                        .paymentStatus("SUCCESS")
                        .orderId(paymentRequest.getOrderId())
                        .referenceNumber(paymentRequest.getReferenceNumber())
                        .amount(paymentRequest.getAmount())
                .build();

        repository.save(transactionDetails);
        log.info("Transaction Completed with Id: {}", transactionDetails.getId());

        return transactionDetails.getId();
    }
}
