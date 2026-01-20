package com.example.demo.controller;

import com.example.demo.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private com.example.demo.Service.CustomerService service;

    @PostMapping
    public Customer create(@RequestBody Customer c) {
        return service.save(c);
    }

    @GetMapping
    public List<Customer> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Customer getById(@PathVariable int id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public Customer update(@PathVariable int id, @RequestBody Customer c) {
        c.setCustomerId(id);
        return service.save(c);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        service.delete(id);
        return "Customer deleted";
    }
}