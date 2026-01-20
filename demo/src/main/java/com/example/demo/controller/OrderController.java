package com.example.demo.controller;

import com.example.demo.model.Order;
import com.example.demo.model.OrderItem;
import com.example.demo.model.OrderRequest;
import com.example.demo.model.OrderResponse;
import com.example.demo.Service.OrderItemService;
import com.example.demo.Service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemService orderItemService;

    @PostMapping("/createWithItems")
    public String createOrderWithItems(@RequestBody OrderRequest request) {

        // 1. Save Order first
        Order savedOrder = orderService.save(request.getOrder());

        // 2. Save Order Items with generated orderId
        for (OrderItem item : request.getItems()) {
            item.setOrderId(savedOrder.getOrderId());
            orderItemService.save(item);
        }


        return "Order and Order Items created successfully";
    }

    @GetMapping("/{orderId}")
    public OrderResponse getOrder(@PathVariable Integer orderId) {
        Order order = orderService.getById(orderId);
        List<OrderItem> items = orderItemService.getByOrderId(orderId);

        OrderResponse response = new OrderResponse();
        response.setOrder(order);
        response.setItems(items);
        return response;
    }
    // 3. Update Order (shipping / billing)
    @PutMapping("/{orderId}")
    public Order updateOrder(@PathVariable Integer orderId, @RequestBody Order updated) {
        updated.setOrderId(orderId);
        return orderService.save(updated);
    }
    // 4. Delete Order + Items
    @DeleteMapping("/{orderId}")
    public String deleteOrder(@PathVariable Integer orderId) {
        orderItemService.deleteByOrderId(orderId); // delete child first
        orderService.delete(orderId);              // delete parent
        return "Order deleted successfully";
    }
    // 5. Add Order Item
    @PostMapping("/{orderId}/items")
    public OrderItem addOrderItem(@PathVariable Integer orderId, @RequestBody OrderItem item) {
        item.setOrderId(orderId);
        return orderItemService.save(item);
    }

    // 6. Update Order Item
    @PutMapping("/{orderId}/items/{itemId}")
    public OrderItem updateOrderItem(@PathVariable Integer orderId,
                                     @PathVariable Integer itemId,
                                     @RequestBody OrderItem item) {
        item.setOrderItemSeqId(itemId);
        item.setOrderId(orderId);
        return orderItemService.save(item);
    }
    @DeleteMapping("/{orderId}/items/{itemId}")
    public String deleteOrderItem(@PathVariable Integer orderId,
                                  @PathVariable Integer itemId) {
        orderItemService.delete(itemId);
        return "Order item deleted successfully";
    }
}