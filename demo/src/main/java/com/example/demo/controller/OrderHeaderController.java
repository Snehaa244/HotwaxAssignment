package com.example.demo.controller;

import com.example.demo.dto.CreateOrderRequest;
import com.example.demo.model.OrderHeader;
import com.example.demo.service.OrderHeaderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderHeaderController {

    private final OrderHeaderService orderHeaderService;

    public OrderHeaderController(OrderHeaderService orderHeaderService) {
        this.orderHeaderService = orderHeaderService;
    }

    // CREATE ORDER
    @PostMapping
    public ResponseEntity<OrderHeader> createOrder(
            @RequestBody CreateOrderRequest request) {

        return new ResponseEntity<>(
                orderHeaderService.createOrder(request),
                HttpStatus.CREATED
        );
    }

    // GET ORDER
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderHeader> getOrder(@PathVariable Integer orderId) {
        return ResponseEntity.ok(orderHeaderService.getOrder(orderId));
    }

    // DELETE ORDER
    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Integer orderId) {
        orderHeaderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }
}
