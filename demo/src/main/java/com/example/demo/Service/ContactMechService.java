package com.example.demo.service;

import com.example.demo.model.ContactMech;
import com.example.demo.model.Customer;
import com.example.demo.repository.ContactMechRepository;
import com.example.demo.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactMechService {

    private final ContactMechRepository contactMechRepository;
    private final CustomerRepository customerRepository;

    public ContactMechService(ContactMechRepository contactMechRepository,
                              CustomerRepository customerRepository) {
        this.contactMechRepository = contactMechRepository;
        this.customerRepository = customerRepository;
    }

    // Create ContactMech
    public ContactMech createContactMech(Integer customerId, ContactMech contactMech) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        contactMech.setCustomer(customer);
        return contactMechRepository.save(contactMech);
    }

    // Get all contact mechs
    public List<ContactMech> getAll() {
        return contactMechRepository.findAll();
    }

    // Get by customer
    public List<ContactMech> getByCustomer(Integer customerId) {
        return contactMechRepository.findByCustomerCustomerId(customerId);
    }

    // Delete
    public void delete(Integer id) {
        contactMechRepository.deleteById(id);
    }
}
