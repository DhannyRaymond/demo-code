package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PartnerDTO implements Serializable {

    @JsonProperty("service_code")
    private String serviceCode;

    @JsonProperty("service_name")
    private String serviceName;

    @JsonProperty("service_icon")
    private String serviceIcon;

    @JsonProperty("service_tariff")
    private Integer serviceTariff;
}
