package com.uni7.ecommerce.api.model.request;

import java.util.Set;

public class OrderItemsRequest {
    Set<OrderProductRequest> items;

    public Set<OrderProductRequest> getItems() {
        return items;
    }

    public void setItems(Set<OrderProductRequest> items) {
        this.items = items;
    }
}
