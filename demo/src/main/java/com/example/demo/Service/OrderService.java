package com.example.demo.Service;

import com.example.demo.model.Order;
import com.example.demo.model.Order;
import com.example.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repo;

    //save
    public Order save(Order o){
        return repo.save(o);
    }

    public List<Order> getAll(){
        return repo.findAll();
    }

    public Order getById(Integer id) {
        return repo.findById(id).orElse(null);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}