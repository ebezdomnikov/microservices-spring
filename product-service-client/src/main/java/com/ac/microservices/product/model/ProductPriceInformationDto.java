package com.ac.microservices.product.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@JsonPropertyOrder()
public class ProductPriceInformationDto {

    @JsonProperty("standard_price")
    private BigDecimal standardPrice;
    @JsonProperty("standard_price_no_vat")
    private BigDecimal standardPriceNoVat;
    private BigDecimal currentPrice;
}
