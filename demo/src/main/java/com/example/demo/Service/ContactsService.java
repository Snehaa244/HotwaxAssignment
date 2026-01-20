package com.example.demo.Service;

import com.example.demo.model.Contacts;
import com.example.demo.repository.ContactsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactsService {


    @Autowired
    private ContactsRepo repo;

    public Contacts save(Contacts c) {
        return repo.save(c);
    }

    public List<Contacts> getAll() {
        return repo.findAll();
    }

    public Contacts getById(int id) {
        return repo.findById(id).orElse(null);
    }

    public void delete(int id) {
        repo.deleteById(id);
    }
}