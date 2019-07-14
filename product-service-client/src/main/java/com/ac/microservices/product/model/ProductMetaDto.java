package com.ac.microservices.product.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductMetaDto {

    @JsonProperty("site_name")
    private String siteName;
    private String description;
    private String keywords;
    private String canonical;
    @JsonProperty("page_title")
    private String pageTitle;
}
