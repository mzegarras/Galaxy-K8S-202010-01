package com.example.lab01.controller.web.dto;

import com.example.lab01.core.domain.Customer;

import java.util.List;

public class CustomersWebDto {

    private List<Customer> customers;

    public CustomersWebDto(List<Customer> customers) {
        this.customers = customers;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
}
