package com.uni7.ecommerce.order;

import com.uni7.ecommerce.customer.Customer;
import com.uni7.ecommerce.data.BaseEntity;
import com.uni7.ecommerce.product.Product;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.EnumType.STRING;

@Entity
@TypeDef(name = "status", typeClass = PostgreSQLEnumType.class)
public class Order extends BaseEntity {

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
    private List<OrderProduct> orderProducts;

    @Enumerated(STRING)
    @Column(columnDefinition = "status")
    @Type(type = "status")
    private Status status;

    @OneToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;



    public Order() {
    }

    public List<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
