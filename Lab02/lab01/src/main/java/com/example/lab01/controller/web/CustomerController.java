package com.example.lab01.controller.web;

import com.example.lab01.controller.web.dto.CustomerWebDto;
import com.example.lab01.controller.web.dto.CustomersWebDto;
import com.example.lab01.core.domain.Customer;
import com.example.lab01.core.dto.CustomerDto;
import com.example.lab01.service.CustomerService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    public static final String X_CORRELATION_ID_HEADER = "X-Correlation-Id";
    public static final String X_API_VERSION_HEADER = "X-Api-Version";
    public static final String X_API_FORCE_SYC_HEADER = "X-Api-Force-Sync";

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public HttpEntity<CustomersWebDto> getList() {

        List<Customer> customers;
        try{
            customers = customerService.getList();
            CustomersWebDto customersWebDto = new CustomersWebDto(customers);
            return new ResponseEntity<>(customersWebDto, HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @GetMapping("/{id}")
    public HttpEntity<CustomerWebDto> getById(@PathVariable("id") int id) {

        Customer customer = customerService.getById(id);

        if(customer!=null){
            CustomerWebDto customerWebDto = new CustomerWebDto();
            customerWebDto.setCustomer(customer);
            return new ResponseEntity<>(customerWebDto, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping
    public HttpEntity<CustomerWebDto> create(@RequestHeader(name = X_API_FORCE_SYC_HEADER, required = false, defaultValue = "false") boolean forceSync,
                       @RequestBody CustomerWebDto customerWebDto) {



        if (forceSync){
            customerService.save(customerWebDto.getCustomer());
            return ResponseEntity.ok().build();
        }else{
            CustomerDto customerDto = new CustomerDto();
            customerDto.setCustomer(customerWebDto.getCustomer());
            customerService.saveAsync(customerDto);


            return ResponseEntity.accepted()
                    .header(X_CORRELATION_ID_HEADER, customerDto.getCorrelation().getCorrelationId())
                    .build();
        }

    }

    @PutMapping("/{id}")
    public HttpEntity<CustomerWebDto> update(@RequestHeader(name = X_API_FORCE_SYC_HEADER, required = false, defaultValue = "false") boolean forceSync,
                       @PathVariable(name = "id") int id,
                       @RequestBody CustomerWebDto customerWebDto) {


        customerWebDto.getCustomer().setCustomerId(id);


        if (forceSync){
            customerService.update(customerWebDto.getCustomer());
            return ResponseEntity.ok().build();
        }else{
            CustomerDto customerDto = new CustomerDto();
            customerDto.setCustomer(customerWebDto.getCustomer());
            customerService.updateAsync(customerDto);

            return ResponseEntity.accepted()
                    .header(X_CORRELATION_ID_HEADER, customerDto.getCorrelation().getCorrelationId())
                    .build();
        }

    }

    @DeleteMapping("/{id}")
    public HttpEntity<Void> delete(
            @RequestHeader(name = X_API_FORCE_SYC_HEADER, required = false, defaultValue = "false") boolean forceSync,
            @PathVariable(name = "id") int id) {


        if(id==666){
            return ResponseEntity.notFound().build();
        }

        Customer customer =new Customer();
        customer.setCustomerId(id);


        if (forceSync){
            customerService.delete(customer);
            return ResponseEntity.ok().build();
        }else{
            CustomerDto customerDto = new CustomerDto();
            customerDto.setCustomer(customer);
            customerService.deleteAsync(customerDto);

            return ResponseEntity.accepted()
                    .header(X_CORRELATION_ID_HEADER, customerDto.getCorrelation().getCorrelationId())
                    .build();
        }

    }

}