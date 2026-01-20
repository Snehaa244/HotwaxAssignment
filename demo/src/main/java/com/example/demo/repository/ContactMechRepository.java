package com.example.demo.repository;

import com.example.demo.model.ContactMech;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactMechRepository extends JpaRepository<ContactMech, Integer> {

    List<ContactMech> findByCustomerCustomerId(Integer customerId);
}
