package com.example.lab01.service;

import com.example.lab01.config.Lab01Properties;
import com.example.lab01.core.domain.Application;
import com.example.lab01.core.domain.Correlation;
import com.example.lab01.core.domain.Customer;
import com.example.lab01.core.domain.EventStatus;
import com.example.lab01.core.dto.CustomerDto;
import com.example.lab01.repository.CustomerRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImplV2 implements CustomerService {

    private CustomerRepository customerRepository;
    private CryptoService cryptoService;
    private Lab01Properties lab01Properties;
    private RabbitTemplate rabbitTemplate;

    public CustomerServiceImplV2(CustomerRepository customerRepository,
                                 CryptoService cryptoService,
                                 Lab01Properties lab01Properties,
                                 RabbitTemplate rabbitTemplate){
        this.customerRepository=customerRepository;
        this.cryptoService=cryptoService;
        this.lab01Properties=lab01Properties;
        this.rabbitTemplate=rabbitTemplate;
    }

    @Override
    public String upper(String input) {
        return input.toUpperCase();
    }

    @Override
    public List<Customer> getList() {

        return customerRepository.getList();
    }

    @Override
    public Customer getById(int id) {
        return customerRepository.getById(id);
    }

    @Override
    public void saveAsync(CustomerDto customerDto) {

        Application application = new Application();
        application.setApplicationId("app01");

        Correlation correlation = new Correlation();
        correlation.setApplication(application);
        correlation.setCorrelationId(RandomStringUtils.randomAlphanumeric(40));
        customerDto.setCorrelation(correlation);
        customerDto.setStatus(EventStatus.PUTTED);

        rabbitTemplate.convertAndSend("clientes",
                "clientes.created.putted",
                customerDto);



    }

    private static final Logger log = LoggerFactory.getLogger(CustomerServiceImplV2.class);

    @RabbitListener(queues = "clientes.eliminar")
    public void receiveDelete(final CustomerDto customerDto) {

        customerDto.setStatus(EventStatus.PROCESSING);
        rabbitTemplate.convertAndSend("clientes",
                "clientes.deleted.processing",
                customerDto);

        try{
            delete(customerDto.getCustomer());

            customerDto.setStatus(EventStatus.SUCCESS);
            rabbitTemplate.convertAndSend("clientes",
                    "clientes.deleted.success",
                    customerDto);

        }catch (Exception ex){
            customerDto.setStatus(EventStatus.FAILED);
            customerDto.setEventError(ex);
            rabbitTemplate.convertAndSend("clientes",
                    "clientes.deleted.failed",
                    customerDto);

        }
    }

    @RabbitListener(queues = "clientes.crear")
    public void receiveMessage(final CustomerDto customerDto) {

        customerDto.setStatus(EventStatus.PROCESSING);

        rabbitTemplate.convertAndSend("clientes",
                "clientes.created.processing",
                customerDto);



        try{

            save(customerDto.getCustomer());

            customerDto.setStatus(EventStatus.SUCCESS);

            rabbitTemplate.convertAndSend("clientes",
                    "clientes.created.success",
                    customerDto);

        }catch (Exception ex){
            customerDto.setStatus(EventStatus.FAILED);
            customerDto.setEventError(ex);
            rabbitTemplate.convertAndSend("clientes",
                    "clientes.created.failed",
                    customerDto);

        }
    }


    @Override
    public void save(Customer customer) {


        if(lab01Properties.isPanicEnabled()){
            throw new RuntimeException();
        }

        log.info("save");
        customer.setPassword(cryptoService.encrypt(customer.getPassword()));

        customerRepository.save(customer);
    }

    @Override
    public void update(Customer customer) {
        log.info("update");
        customerRepository.update(customer);
    }

    @Override
    public void updateAsync(CustomerDto customerDto) {
        Application application = new Application();
        application.setApplicationId("app01");

        Correlation correlation = new Correlation();
        correlation.setApplication(application);
        correlation.setCorrelationId(RandomStringUtils.randomAlphanumeric(40));
        customerDto.setCorrelation(correlation);
        customerDto.setStatus(EventStatus.PUTTED);

        rabbitTemplate.convertAndSend("clientes",
                "clientes.updated.putted",
                customerDto);
    }

    @Override
    public void delete(Customer customer) {
        log.warn("update");
        customerRepository.delete(customer);
    }

    @Override
    public void deleteAsync(CustomerDto customerDto) {
        Application application = new Application();
        application.setApplicationId("app01");

        Correlation correlation = new Correlation();
        correlation.setApplication(application);
        correlation.setCorrelationId(RandomStringUtils.randomAlphanumeric(40));
        customerDto.setCorrelation(correlation);
        customerDto.setStatus(EventStatus.PUTTED);

        rabbitTemplate.convertAndSend("clientes",
                "clientes.deleted.putted",
                customerDto);

    }

    @RabbitListener(queues = "clientes.editar")
    public void receiveUpdate(final CustomerDto customerDto) {

        customerDto.setStatus(EventStatus.PROCESSING);

        rabbitTemplate.convertAndSend("clientes",
                "clientes.updated.processing",
                customerDto);

        try{
            update(customerDto.getCustomer());
            customerDto.setStatus(EventStatus.SUCCESS);
            rabbitTemplate.convertAndSend("clientes",
                    "clientes.updated.success",
                    customerDto);

        }catch (Exception ex){
            customerDto.setStatus(EventStatus.FAILED);
            customerDto.setEventError(ex);
            rabbitTemplate.convertAndSend("clientes",
                    "clientes.updated.failed",
                    customerDto);

        }
    }
}
