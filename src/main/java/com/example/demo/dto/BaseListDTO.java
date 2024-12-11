package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseListDTO {

    @JsonProperty("offset")
    private Integer offset;

    @JsonProperty("limit")
    private Integer limit;
}
