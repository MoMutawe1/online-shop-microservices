package com.onlineshop.OrderService.service;

import com.onlineshop.OrderService.model.OrderRequest;

public interface OrderService {
    String placeOrder(OrderRequest order);
}
