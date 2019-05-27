package com.uni7.ecommerce.api.model.request;

import java.util.Set;

public class StockRequestRequest {
    private Set<StockRequestItem> items;

    public Set<StockRequestItem> getItems() {
        return items;
    }

    public void setItems(Set<StockRequestItem> items) {
        this.items = items;
    }
}
