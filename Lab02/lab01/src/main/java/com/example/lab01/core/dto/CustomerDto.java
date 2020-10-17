package com.example.lab01.core.dto;

import com.example.lab01.core.domain.Correlation;
import com.example.lab01.core.domain.Customer;
import com.example.lab01.core.domain.EventStatus;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;


@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CustomerDto implements Serializable {

    private static final long serialVersionUID = 7193203766462383090L;

    private Correlation correlation;
    private Customer customer;
    private EventStatus status;
    private Exception eventError;

    public Correlation getCorrelation() {
        return correlation;
    }

    public void setCorrelation(Correlation correlation) {
        this.correlation = correlation;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public EventStatus getStatus() {
        return status;
    }

    public void setStatus(EventStatus status) {
        this.status = status;
    }

    public Exception getEventError() {
        return eventError;
    }

    public void setEventError(Exception eventError) {
        this.eventError = eventError;
    }
}
