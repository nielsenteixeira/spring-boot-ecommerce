package com.uni7.ecommerce.api;

import com.uni7.ecommerce.customer.Customer;
import com.uni7.ecommerce.customer.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Customer customer) {
        var inserted = customerService.save(customer);
        return ResponseEntity.ok(inserted);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(customerService.findAll());
    }
}
