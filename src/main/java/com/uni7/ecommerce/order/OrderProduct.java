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
    @JoinColumn(name = "order_id")
    @JsonIgnore
    Order order;

    private int amount;
    private double price;
    private double discount;

    public OrderProduct() {
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
