package com.example.lab01.repository;

import com.example.lab01.core.domain.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.List;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    private final JdbcTemplate jdbcTemplate;
    private final CustomerRowMapper customerRowMapper;




    public CustomerRepositoryImpl(JdbcTemplate jdbcTemplate,
                                  CustomerRowMapper customerRowMapper){
        this.jdbcTemplate=jdbcTemplate;
        this.customerRowMapper=customerRowMapper;

    }


    @Override
    public Customer getById(int id) {

        Customer customer = jdbcTemplate.queryForObject("call GetAllCustomersById(?)", new Object[] { id }, customerRowMapper);
        return customer;
    }

    @Override
    public List<Customer> getList() {
        List<Customer> list = jdbcTemplate.query("call GetAllCustomers",customerRowMapper);
        return list;

    }

    private static final Logger log = LoggerFactory.getLogger(CustomerRepositoryImpl.class);


    @Override
    public int save(Customer customer) {
        log.debug("save");


        return jdbcTemplate.update("call CustomersInsert(?,?,?);",new Object[] { customer.getNombre(),
                                                                             customer.getPaterno(),
                                                    customer.getPassword()});

    }

    @Override
    public void update(Customer customer) {
        log.info("update {}",customer.getCustomerId());

        jdbcTemplate.update("call CustomersUpdate(?,?,?,?);",new Object[] { customer.getCustomerId(),customer.getNombre(),
                customer.getPaterno(),
                customer.getPassword()});

    }

    @Override
    public void delete(Customer customer) {

        log.warn("delete{}",customer.getCustomerId());
        jdbcTemplate.update("call CustomersDelete(?);",new Object[] { customer.getCustomerId()});

    }





}
