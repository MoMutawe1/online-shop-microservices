package com.onlineshop.PaymentService.service;

import com.onlineshop.PaymentService.model.PaymentRequest;

public interface PaymentService {

    String doPayment(PaymentRequest paymentRequest);
}
