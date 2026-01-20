package com.example.demo.Service;

import com.example.demo.model.OrderHeader;
import com.example.demo.model.OrderItem;
import com.example.demo.model.Product;
import com.example.demo.repository.OrderHeaderRepository;
import com.example.demo.repository.OrderItemRepository;
import com.example.demo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderHeaderRepository orderHeaderRepository;
    private final ProductRepository productRepository;

    public OrderItemService(OrderItemRepository orderItemRepository,
                            OrderHeaderRepository orderHeaderRepository,
                            ProductRepository productRepository) {
        this.orderItemRepository = orderItemRepository;
        this.orderHeaderRepository = orderHeaderRepository;
        this.productRepository = productRepository;
    }

    // Add Item
    public OrderItem addItem(Integer orderId, Integer productId, OrderItem item) {

        OrderHeader order = orderHeaderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        Product product = productRepository.findById(Long.valueOf(productId))
                .orElseThrow(() -> new RuntimeException("Product not found"));

        item.setOrderHeader(order);
        item.setProduct(product);

        return orderItemRepository.save(item);
    }

    // Get Items by Order
    public List<OrderItem> getItemsByOrder(Integer orderId) {
        return orderItemRepository.findByOrderHeaderOrderId(orderId);
    }

    // Update Item
    public OrderItem updateItem(Integer itemId, OrderItem updated) {
        OrderItem existing = orderItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Order item not found"));

        existing.setQuantity(updated.getQuantity());
        existing.setStatus(updated.getStatus());

        return orderItemRepository.save(existing);
    }

    // Delete Item
    public void deleteItem(Integer itemId) {
        orderItemRepository.deleteById(itemId);
    }
}
