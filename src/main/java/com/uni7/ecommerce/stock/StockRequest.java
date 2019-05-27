package com.uni7.ecommerce.stock;

import com.uni7.ecommerce.data.BaseEntity;
import com.uni7.ecommerce.order.Status;
import com.uni7.ecommerce.product.Product;
import org.hibernate.annotations.Type;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;

@Entity
public class StockRequest extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;
    private int amount;

    @Enumerated(STRING)
    @Column(columnDefinition = "status")
    @Type(type = "status")
    private Status status;

    public StockRequest() {
    }

    public StockRequest(Product product, int amount, Status status) {
        this.product = product;
        this.amount = amount;
        this.status = status;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}