package com.uni7.ecommerce.api.model.response;

import com.uni7.ecommerce.stock.StockItem;

public class StockItemResponse {

    private long stockItemId;
    private long productId;
    private String name;
    private String description;
    private int amount;
    private double price;

    public StockItemResponse(StockItem stockItem) {
        this.stockItemId = stockItem.getId();
        this.productId = stockItem.getProduct().getId();
        this.name =stockItem.getProduct().getName();
        this.description =stockItem.getProduct().getDescription();
        this.amount =stockItem.getAmount();
        this.price = stockItem.getPrice();
    }

    public long getStockItemId() {
        return stockItemId;
    }

    public long getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getAmount() {
        return amount;
    }

    public double getPrice() {
        return price;
    }
}
