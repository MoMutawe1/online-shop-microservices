package com.onlineshop.OrderService.service;

import com.onlineshop.OrderService.entity.OrderEntity;
import com.onlineshop.OrderService.external.client.PaymentService;
import com.onlineshop.OrderService.external.client.ProductService;
import com.onlineshop.OrderService.external.request.PaymentRequest;
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

    @Autowired
    ProductService productService;

    @Autowired
    private PaymentService paymentService;

    @Override
    public String placeOrder(OrderRequest order) {

        log.info("Placing order request: {}.", order);

        // Rest API call using FeignClient to Product Service- verify if we have enough items tnen Reduce the Quantity in the Product Service.
        productService.reduceQuantity(order.getProductId(), order.getQuantity());

        log.info("Creating Order with status CREATED.");
        // Create Order Entity: save the data with status Order Created.
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

        // Call Payment Service: Proceed with the Payment (If payment success then mark it as PLACED, else PAYMENT_FAILED).
        log.info("Calling Payment Service to complete the payment.");
        PaymentRequest paymentRequest = PaymentRequest.builder()
                .orderId(orderEntity.getOrderId())
                .paymentMode(order.getPaymentMode())
                .amount(order.getAmount())
                .build();

        String orderStatus = null;
        try{
            paymentService.doPayment(paymentRequest);
            log.info("Payment done successfully, changing order status to PLACED.");
            orderStatus = "PLACED";
        } catch (Exception e){
            log.info("Error occurred in payment, Changing order status to PAYMENT_FAILED");
            orderStatus = "PAYMENT_FAILED";
        }

        orderEntity.setOrderStatus(orderStatus);
        orderRepository.save(orderEntity);

        log.info("Order Places Successfully with Order Id: {}.", orderEntity.getOrderId());
        return orderEntity.getOrderId();
    }
}
