package com.ac.microservices.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Embeddable;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@Embeddable
public class ProductPricingInformation {

    @JsonProperty("standard_price")
    @DecimalMin(value = "0", groups = {Event.ValidationCreate.class, UpdateProductEvent.ValidationUpdate.class})
    private BigDecimal standardPrice;

    @DecimalMin(value = "0", groups = {Event.ValidationCreate.class, UpdateProductEvent.ValidationUpdate.class})
    @JsonProperty("standard_price_no_vat")
    private BigDecimal standardPriceNoVat;

    @DecimalMin(value = "0", groups = {Event.ValidationCreate.class, UpdateProductEvent.ValidationUpdate.class})
    private BigDecimal currentPrice;
}
