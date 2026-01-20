package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name="customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;

    private String firstName;
    private String lastName;

    public void setCustomerId(int id) {
    }
}