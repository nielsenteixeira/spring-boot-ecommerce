package com.uni7.ecommerce.customer;

import com.uni7.ecommerce.data.BaseEntity;

import javax.persistence.Entity;

@Entity
public class Customer extends BaseEntity {

    private String name;

    public Customer() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
