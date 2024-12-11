package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
@Table(name = "PARTNER")
public class Partner implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id")
    private String id;

    @NotNull
    @Column(name = "service_code", length = 100)
    private String serviceCode;

    @NotNull
    @Column(name = "service_name", length = 100)
    private String serviceName;

    @Column(name = "service_icon", length = 100)
    private String serviceIcon;

    @NotNull
    @Column(name = "service_tariff", length = 15)
    private Integer serviceTariff = 0;
}
