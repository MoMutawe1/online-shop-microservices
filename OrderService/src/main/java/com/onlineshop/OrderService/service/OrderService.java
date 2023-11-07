package com.onlineshop.OrderService.service;

import com.onlineshop.OrderService.model.OrderRequest;
import com.onlineshop.OrderService.model.OrderResponse;

public interface OrderService {
    String placeOrder(OrderRequest order);
    OrderResponse getOrderDetails(String orderId);
}
