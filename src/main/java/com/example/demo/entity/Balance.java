package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "BALANCE")
public class Balance implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id")
    private String id;

    @OneToOne
    @JoinColumn(name = "membership_id", nullable = false)
    private Membership membership;

    @NotNull
    @Column(name = "balance", length = 50)
    private Integer balance = 0;

    public Balance(Membership membership, Integer balance) {
        this.membership = membership;
        this.balance = balance;
    }
}
