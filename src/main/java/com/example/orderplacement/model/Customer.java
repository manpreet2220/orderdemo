package com.example.orderplacement.model;

import javax.persistence.*;

@Entity
public class Customer {

    @SequenceGenerator(name = "CUSTOMER_ID_SEQ", sequenceName = "CUSTOMER_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUSTOMER_ID_SEQ")
    @Id
    @Column(name="customer_id")
    private Long customerId;

    private String name;

    private String email;

    private long phoneNumber;

    public Customer() {
    }

    public Customer(String name, String email, long phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
    public Customer(String name, String email, long phoneNumber,long customerId) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.customerId=customerId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
