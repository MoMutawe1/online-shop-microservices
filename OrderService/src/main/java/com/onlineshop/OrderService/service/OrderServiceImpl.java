package com.onlineshop.OrderService.service;

import com.onlineshop.OrderService.entity.OrderEntity;
import com.onlineshop.OrderService.model.OrderRequest;
import com.onlineshop.OrderService.repository.OrderRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Log4j2
@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderRepository orderRepository;

    @Override
    public String placeOrder(OrderRequest order) {
        // Create Order Entity: save the data with status Order Created.
        // Call Product Service: Block Products (Check if we have required quantity in the inventory then Reduce the Quantity)
        // Call Payment Service: Proceed with the Payment (If payment Success then mark it as COMPLETE, else CANCELLED).
        log.info("Placing order request: {}", order);

        OrderEntity orderEntity =
                OrderEntity.builder()
                        .productId(order.getProductId())
                        .quantity(order.getQuantity())
                        .orderStatus("CREATED")
                        .orderDate(Instant.now())
                        .amount(order.getAmount())
                        .build();

        orderEntity.setOrderId(UUID.randomUUID().toString());
        orderRepository.save(orderEntity);
        log.info("Order Places Successfully with Order Id: {}", orderEntity.getOrderId());
        return orderEntity.getOrderId();
    }
}
