package com.uni7.ecommerce.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uni7.ecommerce.data.BaseEntity;
import com.uni7.ecommerce.product.Product;

import javax.persistence.*;

@Entity
public class OrderProduct extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_order_id")
    @JsonIgnore
    Order order;

    private int amount;
    private double totalPrice;

    public OrderProduct() {
    }

    public OrderProduct(Product product, int amount) {
        this.product = product;
        this.amount = amount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int ammount) {
        this.amount = ammount;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
