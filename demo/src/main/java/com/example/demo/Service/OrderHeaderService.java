package com.example.demo.Service;

import com.example.demo.dto.CreateOrderRequest;
import com.example.demo.dto.OrderItemRequest;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class OrderHeaderService {

    private final OrderHeaderRepository orderHeaderRepository;
    private final CustomerRepository customerRepository;
    private final ContactMechRepository contactMechRepository;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderHeaderService(OrderHeaderRepository orderHeaderRepository,
                              CustomerRepository customerRepository,
                              ContactMechRepository contactMechRepository,
                              ProductRepository productRepository,
                              OrderItemRepository orderItemRepository) {
        this.orderHeaderRepository = orderHeaderRepository;
        this.customerRepository = customerRepository;
        this.contactMechRepository = contactMechRepository;
        this.productRepository = productRepository;
        this.orderItemRepository = orderItemRepository;
    }

    // CREATE ORDER WITH ITEMS
    @Transactional
    public OrderHeader createOrder(CreateOrderRequest request) {

        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        ContactMech shipping = contactMechRepository
                .findById(request.getShippingContactMechId())
                .orElseThrow(() -> new RuntimeException("Shipping contact not found"));

        ContactMech billing = contactMechRepository
                .findById(request.getBillingContactMechId())
                .orElseThrow(() -> new RuntimeException("Billing contact not found"));

        OrderHeader order = new OrderHeader();
        order.setOrderDate(
                request.getOrderDate() != null ? request.getOrderDate() : LocalDate.now()
        );
        order.setCustomer(customer);
        order.setShippingContactMech(shipping);
        order.setBillingContactMech(billing);

        OrderHeader savedOrder = orderHeaderRepository.save(order);

        if (request.getOrderItems() != null && !request.getOrderItems().isEmpty()) {
            for (OrderItemRequest itemReq : request.getOrderItems()) {

                Product product = productRepository.findById(itemReq.getProductId())
                        .orElseThrow(() -> new RuntimeException("Product not found"));

                OrderItem item = new OrderItem();
                item.setOrderHeader(savedOrder);
                item.setProduct(product);
                item.setQuantity(itemReq.getQuantity());
                item.setStatus(
                        itemReq.getStatus() != null ? itemReq.getStatus() : "CREATED"
                );

                orderItemRepository.save(item);
            }
        }

        return savedOrder;
    }

    public OrderHeader getOrder(Integer orderId) {
        return orderHeaderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public void deleteOrder(Integer orderId) {
        orderHeaderRepository.deleteById(orderId);
    }
}
