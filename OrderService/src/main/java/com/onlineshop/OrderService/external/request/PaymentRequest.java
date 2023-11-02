package com.onlineshop.OrderService.external.request;

import com.onlineshop.OrderService.model.PaymentMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {
    private String orderId;
    private long amount;
    private String referenceNumber;
    private PaymentMode paymentMode;
}
