package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
public class PaymentDTO implements Serializable {

    @JsonProperty("invoice_number")
    private String invoiceNumber;

    @JsonProperty("service_code")
    private String serviceCode;

    @JsonProperty("service_name")
    private String serviceName;

    @JsonProperty("transaction_type")
    private String transactionType;

    @JsonProperty("total_amount")
    private Integer totalAmount;

    @JsonProperty("created_on")
    private Instant createdOn;

    public PaymentDTO(String invoiceNumber, String serviceCode, String serviceName, String transactionType, Integer totalAmount, Instant createdOn) {
        this.invoiceNumber = invoiceNumber;
        this.serviceCode = serviceCode;
        this.serviceName = serviceName;
        this.transactionType = transactionType;
        this.totalAmount = totalAmount;
        this.createdOn = createdOn;
    }
}
