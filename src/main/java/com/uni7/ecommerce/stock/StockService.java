package com.uni7.ecommerce.stock;

import org.springframework.stereotype.Service;

@Service
public class StockService {

    private final ProductStockRepository productStockRepository;
    private final StockRequestRepository stockRequestRepository;

    public StockService(ProductStockRepository productStockRepository, StockRequestRepository stockRequestRepository) {
        this.productStockRepository = productStockRepository;
        this.stockRequestRepository = stockRequestRepository;
    }
}
