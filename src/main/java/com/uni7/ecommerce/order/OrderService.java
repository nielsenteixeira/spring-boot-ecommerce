package com.uni7.ecommerce.order;

import com.uni7.ecommerce.customer.Customer;
import com.uni7.ecommerce.exception.InvalidProductException;
import com.uni7.ecommerce.stock.StockItem;
import com.uni7.ecommerce.stock.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final StockService stockService;
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    public OrderService(OrderRepository orderRepository, StockService stockService) {
        this.orderRepository = orderRepository;
        this.stockService = stockService;
    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public void deleteById(Long id) {
        try {
            orderRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            logger.warn("user attempted to delete a non-existent client. Id: {}.", id);
        }
    }

    public Optional<Order> findById(long id) {
        return orderRepository.findById(id);
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Transactional
    public Order createOrder(Customer customer, List<OrderProduct> orderProducts) throws InvalidProductException {
        var order = new Order(Status.OPEN, customer);
        boolean hasStockAvailable = true;

        for(OrderProduct orderProduct: orderProducts) {
            var stockItem = stockService.findByProductId(orderProduct.getProduct().getId());

            if(stockItem.isPresent()){
                if(hasAvailableProductInStock(stockItem.get(),  orderProduct.getAmount())) {
                    removeProductFromStock(stockItem.get(), orderProduct.getAmount());
                } else {
                    hasStockAvailable = false;
                }
                orderProduct.setOrder(order);
                orderProduct.setTotalPrice(stockItem.get().getPrice() * orderProduct.getAmount());
            } else {
                throw new InvalidProductException("stock item not found. productId: " + orderProduct.getProduct().getId());
            }
        }

        if(hasStockAvailable) {
            order.setStatus(Status.FINALIZED);
        }

        order.setOrderProducts(orderProducts);
        return orderRepository.save(order);
    }

    private boolean hasAvailableProductInStock(StockItem stockItem, int amount) {
        return stockItem.getAmount() >= amount;
    }

    private StockItem removeProductFromStock(StockItem stockItem, int amount) {
        stockItem.setAmount(stockItem.getAmount() - amount);
        return stockService.save(stockItem);
    }


}
