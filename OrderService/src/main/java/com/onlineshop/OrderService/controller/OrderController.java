package com.onlineshop.OrderService.controller;

import com.onlineshop.OrderService.model.OrderRequest;
import com.onlineshop.OrderService.model.OrderResponse;
import com.onlineshop.OrderService.service.OrderService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrderDetails(@PathVariable("orderId") String orderId){
        OrderResponse orderResponse =
                orderService.getOrderDetails(orderId);
        return new ResponseEntity<>(orderResponse, HttpStatus.OK);
    }

    @PostMapping("/placeOrder")
    public ResponseEntity<String> placeOrder(@RequestBody OrderRequest order){
        String orderId = orderService.placeOrder(order);
        log.info("Order Id: {}", orderId);
        String responseMsg = "Order with Id: " + orderId + ", has been placed.";
        return new ResponseEntity<>(responseMsg, HttpStatus.CREATED);
    }

}
