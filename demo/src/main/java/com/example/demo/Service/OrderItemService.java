package com.example.demo.Service;

import com.example.demo.model.Order;
import com.example.demo.model.OrderItem;
import com.example.demo.repository.OrderItemRepository;
import com.example.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {


    @Autowired
    private OrderItemRepository repo;

    public OrderItem save(OrderItem item) {
        return repo.save(item);
    }

    public List<OrderItem> getAll() {
        return repo.findAll();
    }
    public List<OrderItem> getByOrderId(Integer orderId) {
        return repo.findByOrderId(orderId);
    }

    public OrderItem getById(Integer id) {
        return repo.findById(id).orElse(null);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }

    public void deleteByOrderId(Integer orderId) {
        List<OrderItem> items = repo.findByOrderId(orderId);
        repo.deleteAll(items);
    }
}