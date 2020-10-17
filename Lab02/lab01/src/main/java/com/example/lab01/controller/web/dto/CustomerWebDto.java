package com.example.lab01.controller.web.dto;



import com.example.lab01.core.domain.Customer;

public class CustomerWebDto {

    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
