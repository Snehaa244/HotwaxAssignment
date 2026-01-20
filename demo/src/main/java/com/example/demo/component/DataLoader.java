package com.example.demo.component;

import com.example.demo.model.ContactMech;
import com.example.demo.model.Customer;
import com.example.demo.model.Product;
import com.example.demo.repository.ContactMechRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final CustomerRepository customerRepository;
    private final ContactMechRepository contactMechRepository;
    private final ProductRepository productRepository;

    public DataLoader(CustomerRepository customerRepository,
                      ContactMechRepository contactMechRepository,
                      ProductRepository productRepository) {
        this.customerRepository = customerRepository;
        this.contactMechRepository = contactMechRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Create Customer
        if (customerRepository.count() == 0) {
            Customer john = new Customer("John", "Doe");
            john = customerRepository.save(john);

            // Create ContactMechs
            ContactMech shipping = new ContactMech();
            shipping.setCustomer(john);
            shipping.setStreetAddress("123 Shipping St");
            shipping.setCity("New York");
            shipping.setState("NY");
            shipping.setPostalCode("10001");
            shipping.setPhoneNumber("555-0101");
            shipping.setEmail("john@example.com");
            contactMechRepository.save(shipping);

            ContactMech billing = new ContactMech();
            billing.setCustomer(john);
            billing.setStreetAddress("456 Billing Ave");
            billing.setCity("New York");
            billing.setState("NY");
            billing.setPostalCode("10001");
            billing.setPhoneNumber("555-0102");
            billing.setEmail("john@billing.com");
            contactMechRepository.save(billing);

            // Create Products
            productRepository.save(new Product(null, "T-Shirt", 19.99, 100));
            productRepository.save(new Product(null, "Jeans", 49.99, 50));
            productRepository.save(new Product(null, "Sneakers", 89.99, 30));

            System.out.println("Data Loaded: John Doe, Contacts, and Products (T-Shirt, Jeans, Sneakers)");
        }
    }
}
