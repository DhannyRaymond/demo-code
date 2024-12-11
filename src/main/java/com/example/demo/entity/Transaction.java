package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "TRANSACTION")
public class Transaction implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id")
    private String id;

    @ManyToOne
    @JoinColumn(name = "membership_id", nullable = false)
    private Membership membership;

    @NotNull
    @Column(name = "invoice_number", length = 100)
    private String invoiceNumber;

    @NotNull
    @Column(name = "transaction_type", length = 50)
    private String transactionType;

    @Column(name = "description", length = 100)
    private String description;

    @NotNull
    @Column(name = "amount", length = 50)
    private Integer totalAmount = 0;

    @CreatedDate
    @Column(name = "created_on", updatable = false)
    private Instant createdOn = Instant.now();

    public Transaction(Membership membership, String invoiceNumber, String transactionType, String description, Integer totalAmount) {
        this.membership = membership;
        this.invoiceNumber = invoiceNumber;
        this.transactionType = transactionType;
        this.description = description;
        this.totalAmount = totalAmount;
    }
}
