package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class BannerDTO implements Serializable {

    @JsonProperty("banner_name")
    private String bannerName;

    @JsonProperty("banner_image")
    private String bannerImage;

    @JsonProperty("description")
    private String description;
}
