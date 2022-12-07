package com.domain.models.entities;

public class Result {

    private Long id;
    private String customer_id;
    private String msisdn;
    private String value;

    public Result() {

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
        return "Result [id=" + id + ", customer_id=" + customer_id + ", msisdn=" + msisdn + ", value=" + value + "]";
    }
}
