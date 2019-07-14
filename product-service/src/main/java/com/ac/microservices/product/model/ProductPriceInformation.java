package com.ac.microservices.product.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProductPriceInformation {

    @Column(name = "priceInformationStandardPrice")
    @JsonProperty("standard_price")
    private BigDecimal standardPrice;

    @Column(name = "priceInformationStandardPriceNoVat")
    @JsonProperty("standard_price_no_vat")
    private BigDecimal standardPriceNoVat;

    @Column(name = "priceInformationCurrentPrice")
    @JsonProperty("currentPrice")
    private BigDecimal currentPrice;
}
