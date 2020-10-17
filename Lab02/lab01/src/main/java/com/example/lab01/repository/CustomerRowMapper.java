package com.example.lab01.repository;

import com.example.lab01.core.domain.Customer;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CustomerRowMapper implements RowMapper<Customer> {
    @Override
    public Customer mapRow(ResultSet rs, int i) throws SQLException {
        Customer customer = new Customer();
        customer.setCustomerId(rs.getInt("id"));
        customer.setNombre(rs.getString("name"));
        customer.setPaterno(rs.getString("lastname"));
        return customer;
    }
}
