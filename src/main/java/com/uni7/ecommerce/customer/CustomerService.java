package com.uni7.ecommerce.customer;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }

    public Optional<Customer> findById(long id) {
        return customerRepository.findById(id);
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }
}
