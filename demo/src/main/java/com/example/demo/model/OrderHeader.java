package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "order_header")
public class OrderHeader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "order_date", nullable = false)
    private LocalDate orderDate;

    // ---------------- RELATIONSHIPS ----------------

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shipping_contact_mech_id", nullable = false)
    private ContactMech shippingContactMech;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "billing_contact_mech_id", nullable = false)
    private ContactMech billingContactMech;

    @OneToMany(
            mappedBy = "orderHeader",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<OrderItem> orderItems;

    // ---------------- CONSTRUCTORS ----------------

    public OrderHeader() {
    }

    // ---------------- GETTERS & SETTERS ----------------

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ContactMech getShippingContactMech() {
        return shippingContactMech;
    }

    public void setShippingContactMech(ContactMech shippingContactMech) {
        this.shippingContactMech = shippingContactMech;
    }

    public ContactMech getBillingContactMech() {
        return billingContactMech;
    }

    public void setBillingContactMech(ContactMech billingContactMech) {
        this.billingContactMech = billingContactMech;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
