package com.ac.microservices.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Getter
@Setter
@Embeddable
public class ProductPricingInformation {

    @NotBlank(message = "Standard price name is mandatory")
    @JsonProperty("standard_price")
    private BigDecimal standardPrice;

    @NotBlank(message = "Standard price no vat is mandatory")
    @JsonProperty("standard_price_no_vat")
    private BigDecimal standardPriceNoVat;

    @NotBlank(message = "Standard price is mandatory")
    private BigDecimal currentPrice;
}
