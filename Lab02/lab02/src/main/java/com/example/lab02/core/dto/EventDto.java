package com.example.lab02.core.dto;

import com.example.lab02.core.domain.Correlation;
import com.example.lab02.core.domain.Customer;
import com.example.lab02.core.domain.EventStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;


@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class EventDto implements Serializable {

    private static final long serialVersionUID = 7193203766462383090L;

    private Correlation correlation;
    //private Customer customer;
    private EventStatus status;
    private Exception eventError;

    public Correlation getCorrelation() {
        return correlation;
    }

    public void setCorrelation(Correlation correlation) {
        this.correlation = correlation;
    }

    /*public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }*/

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

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, true);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj, true);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

}
