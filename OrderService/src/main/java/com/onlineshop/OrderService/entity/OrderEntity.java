package com.onlineshop.OrderService.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ORDER_DETAILS")
public class OrderEntity {
    @Id
    private String orderId;
    @Column(name = "PRODUCT_ID")
    private String productId;
    @Column(name = "QUANTITY")
    private long quantity;
    @Column(name = "ORDER_DATE")
    private Instant orderDate;
    @Column(name = "ORDER_STATUS")
    private String orderStatus;
    @Column(name = "TOTAL_AMOUNT")
    private long amount;
}
