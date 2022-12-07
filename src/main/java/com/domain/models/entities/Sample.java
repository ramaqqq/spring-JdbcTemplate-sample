package com.domain.models.entities;

import jakarta.persistence.Id;

public class Sample {

    private Long id;
    private String customer_id;
    private String msisdn;
    private String value;

    public Sample() {

    }

    public Sample(Long id, String customer_id, String msisdn, String value) {
        this.id = id;
        this.customer_id = customer_id;
        this.msisdn = msisdn;
        this.value = value;
    }

    public Sample(String customer_id, String msisdn, String value) {
        this.customer_id = customer_id;
        this.msisdn = msisdn;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Sample [id=" + id + ", customer_id=" + customer_id + ", msisdn=" + msisdn + ", value=" + value + "]";
    }



}
