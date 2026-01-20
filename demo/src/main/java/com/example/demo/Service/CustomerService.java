package com.example.demo.service;

import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // Create
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    // Read All
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // Read By ID
    public Customer getCustomerById(Integer id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id " + id));
    }

    // Update
    public Customer updateCustomer(Integer id, Customer updatedCustomer) {
        Customer existing = getCustomerById(id);
        existing.setFirstName(updatedCustomer.getFirstName());
        existing.setLastName(updatedCustomer.getLastName());
        return customerRepository.save(existing);
    }

    // Delete
    public void deleteCustomer(Integer id) {
        customerRepository.deleteById(id);
    }
}
