package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "orders_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderItemSeqId;

    private Integer orderId;      // FK to ordersp
    private Integer productId;

    private int quantity;
    private String status;

    public Integer getOrderId() {
        return orderId;
    }

    // setter
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }


    public Integer getOrderItemSeqId() {
        return orderItemSeqId;
    }

    public void setOrderItemSeqId(Integer orderItemSeqId) {
        this.orderItemSeqId = orderItemSeqId;
    }
}