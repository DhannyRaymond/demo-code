package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
public class TransactionDTO implements Serializable {

    @JsonProperty("invoice_number")
    private String invoiceNumber;

    @JsonProperty("transaction_type")
    private String transactionType;

    @JsonProperty("description")
    private String description;

    @JsonProperty("total_amount")
    private Integer totalAmount;

    @JsonProperty("created_on")
    private Instant createdOn;
}
