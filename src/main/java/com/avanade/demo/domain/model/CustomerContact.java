package com.avanade.demo.domain.model;

import jakarta.persistence.*;

@Entity
@Table(name = "customer_contacts")

public class CustomerContact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "customer_contact_type_id", nullable = false)
    private CustomerContactType customerContactType;

    @Column(nullable = false)
    private String contactValue;

    public Long getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public CustomerContactType getCustomerContactType() {
        return customerContactType;
    }

    public String getContactValue() {
        return contactValue;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setCustomerContactType(CustomerContactType customerContactType) {
        this.customerContactType = customerContactType;
    }

    public void setContactValue(String contactValue) {
        this.contactValue = contactValue;
    }
}