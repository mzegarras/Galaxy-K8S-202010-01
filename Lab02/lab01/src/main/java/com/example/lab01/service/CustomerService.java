package com.example.lab01.service;

import com.example.lab01.core.domain.Customer;
import com.example.lab01.core.dto.CustomerDto;

import java.util.List;

public interface CustomerService {
    String upper(String input);
    List<Customer> getList();
    Customer getById(int id);

    void saveAsync(CustomerDto customer);
    void save(Customer customer);
    void update(Customer customer) ;
    void updateAsync(CustomerDto customer) ;
    void delete(Customer customer);
    void deleteAsync(CustomerDto customer);



}
