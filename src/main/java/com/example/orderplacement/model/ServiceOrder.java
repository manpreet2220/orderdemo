package com.example.orderplacement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ServiceOrder {

    @SequenceGenerator(name = "SERVICE_ORDER_ID_SEQ", sequenceName = "SERVICE_ORDER_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SERVICE_ORDER_ID_SEQ")
    @Id
    private Long serviceOrderId;

    private String fromAddress;

    private String toAddress;

    private String serviceType;

    private Date serviceDate;

    private String note;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id"
    )
    private Customer customer;

    public ServiceOrder() {
    }

    public ServiceOrder(String fromAddress, String toAddress, String serviceType, Date serviceDate, String note) {
        this.fromAddress = fromAddress;
        this.toAddress = toAddress;
        this.serviceType = serviceType;
        this.serviceDate = serviceDate;
        this.note = note;
    }

    public ServiceOrder(String fromAddress, String toAddress, String serviceType, Date serviceDate, String note,
                        Customer customer,long serviceOrderId) {
        this.fromAddress = fromAddress;
        this.toAddress = toAddress;
        this.serviceType = serviceType;
        this.serviceDate = serviceDate;
        this.note = note;
        this.customer = customer;
        this.serviceOrderId=serviceOrderId;
    }

    public Long getServiceOrderId() {
        return serviceOrderId;
    }

    public void setServiceOrderId(Long serviceOrderId) {
        this.serviceOrderId = serviceOrderId;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public Date getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(Date serviceDate) {
        this.serviceDate = serviceDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
