package com.onlineshop.OrderService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

    private String productId;
    private PaymentMode paymentMode;
    private long quantity;
    private long amount;
}
