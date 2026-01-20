package com.example.demo.controller;

import com.example.demo.model.ContactMech;
import com.example.demo.service.ContactMechService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contact-mechs")
public class ContactMechController {

    private final ContactMechService contactMechService;

    public ContactMechController(ContactMechService contactMechService) {
        this.contactMechService = contactMechService;
    }

    // Create contact mech for a customer
    @PostMapping("/customer/{customerId}")
    public ResponseEntity<ContactMech> create(
            @PathVariable Integer customerId,
            @RequestBody ContactMech contactMech) {
        return new ResponseEntity<>(
                contactMechService.createContactMech(customerId, contactMech),
                HttpStatus.CREATED);
    }

    // Get all contact mechs
    @GetMapping
    public ResponseEntity<List<ContactMech>> getAll() {
        return ResponseEntity.ok(contactMechService.getAll());
    }

    // Get contact mechs by customer
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<ContactMech>> getByCustomer(@PathVariable Integer customerId) {
        return ResponseEntity.ok(contactMechService.getByCustomer(customerId));
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        contactMechService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
