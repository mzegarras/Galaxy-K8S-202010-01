package com.example.lab01.service;


import com.example.lab01.core.domain.Customer;
import com.example.lab01.core.dto.CustomerDto;
import org.springframework.stereotype.Service;

import java.util.List;


public class CustomerServiceImpl implements CustomerService {

    @Override
    public String upper(String input) {
        return input.toUpperCase();
    }

    @Override
    public List<Customer> getList() {
        return null;
    }

    @Override
    public Customer getById(int id) {
        return null;
    }

    @Override
    public void saveAsync(CustomerDto customer) {

    }

    @Override
    public void save(Customer customer) {

    }

    @Override
    public void update(Customer customer) {

    }

    @Override
    public void updateAsync(CustomerDto customer) {

    }

    @Override
    public void delete(Customer customer) {

    }

    @Override
    public void deleteAsync(CustomerDto customer) {

    }


}
