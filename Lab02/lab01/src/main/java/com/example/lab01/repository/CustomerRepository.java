package com.example.lab01.repository;


import com.example.lab01.core.domain.Customer;

import java.util.List;

public interface CustomerRepository {

    Customer getById(int id);
    List<Customer> getList();
    int save(Customer customer);
    void update(Customer customer) ;
    void delete(Customer customer);
}
