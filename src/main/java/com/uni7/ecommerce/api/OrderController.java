package com.uni7.ecommerce.api;

import com.uni7.ecommerce.api.model.request.OrderItemsRequest;
import com.uni7.ecommerce.api.model.request.OrderProductRequest;
import com.uni7.ecommerce.customer.CustomerService;
import com.uni7.ecommerce.exception.InvalidProductException;
import com.uni7.ecommerce.order.OrderProduct;
import com.uni7.ecommerce.order.OrderService;
import com.uni7.ecommerce.product.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "/customers/{customerId}/orders")
public class OrderController {

    private final CustomerService customerService;
    private final ProductService productService;
    private final OrderService orderService;

    public OrderController(CustomerService customerService, ProductService productService, OrderService orderService) {
        this.customerService = customerService;
        this.productService = productService;
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@PathVariable("customerId") long customerId, @RequestBody OrderItemsRequest orderItemsRequest) {
        var customer = customerService.findById(customerId);

        if(!customer.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        var orderProducts = new ArrayList<OrderProduct>();

        for(OrderProductRequest orderProductRequest: orderItemsRequest.getItems()){
            var product = productService.findById(orderProductRequest.getId());
            if(!product.isPresent()) {
                return ResponseEntity.badRequest().body("invalid productId: " + orderProductRequest.getId());
            }
            orderProducts.add(new OrderProduct(product.get(), orderProductRequest.getAmount()));
        }

        try {
            var order = orderService.createOrder(customer.get(), orderProducts);
            return ResponseEntity.ok(order);
        } catch (InvalidProductException e) {
            return ResponseEntity.badRequest().body("product not available in stock. detail: " + e.getMessage());
        }

    }
}
