package com.ac.microservices.product.model;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Setter
@Builder
@ToString
public class ProductDto {

    private String id;
    private String name;

    @JsonProperty("model_number")
    private String modelNumber;

    @JsonProperty("product_type")
    private String productType;

    @JsonProperty("meta_data")
    private ProductMetaDto meta;

    @JsonProperty("pricing_information")
    private ProductPriceInformationDto priceInformation;

    @JsonProperty("product_description")
    private ProductDescriptionDto productDescription;
}
