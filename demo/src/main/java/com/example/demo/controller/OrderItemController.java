package com.example.demo.controller;

import com.example.demo.model.OrderItem;
import com.example.demo.Service.OrderItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders/{orderId}/items")
public class OrderItemController {

    private final OrderItemService orderItemService;

    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    // Add Order Item
    @PostMapping
    public ResponseEntity<OrderItem> addItem(
            @PathVariable Integer orderId,
            @RequestParam Integer productId,
            @RequestBody OrderItem item) {

        return new ResponseEntity<>(
                orderItemService.addItem(orderId, productId, item),
                HttpStatus.CREATED);
    }

    // Get Items by Order
    @GetMapping
    public ResponseEntity<List<OrderItem>> getItems(@PathVariable Integer orderId) {
        return ResponseEntity.ok(orderItemService.getItemsByOrder(orderId));
    }

    // Update Item
    @PutMapping("/{itemId}")
    public ResponseEntity<OrderItem> updateItem(
            @PathVariable Integer itemId,
            @RequestBody OrderItem item) {

        return ResponseEntity.ok(orderItemService.updateItem(itemId, item));
    }

    // Delete Item
    @DeleteMapping("/{itemId}")
    public ResponseEntity<Void> deleteItem(@PathVariable Integer itemId) {
        orderItemService.deleteItem(itemId);
        return ResponseEntity.noContent().build();
    }
}
