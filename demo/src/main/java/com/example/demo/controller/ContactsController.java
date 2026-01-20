package com.example.demo.controller;

import com.example.demo.Service.ContactsService;
import com.example.demo.model.Contacts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactsController {


    @Autowired
    private ContactsService service;

    @PostMapping
    public Contacts create(@RequestBody Contacts c) {
        return service.save(c);
    }

    @GetMapping
    public List<Contacts> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Contacts get(@PathVariable int id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        service.delete(id);
        return "Contact deleted";
    }
}