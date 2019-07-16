package com.ac.microservices.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString(callSuper = true)
@Entity
@DiscriminatorValue("UpdateProductEvent")
@JsonDeserialize(using = UpdateProductDeserializer.class)
public class UpdateProductEvent extends Event {

    @NotBlank(message = "Id is mandatory", groups = {Event.ValidationUpdate.class})
    @NotNull(message = "Id can't be null", groups = {Event.ValidationUpdate.class})
    @Length(min = 1, max = 255, groups = {Event.ValidationUpdate.class})
    @JsonProperty("id")
    @Column(name = "product_id")
    private String productId;

    private String name;

    @Length(min = 1, max = 255, groups = {Event.ValidationUpdate.class})
    @JsonProperty("model_number")
    private String modelNumber;

    @Length(min = 1, max = 255, groups = {Event.ValidationUpdate.class})
    @JsonProperty("product_type")
    private String productType;

    @JsonProperty("meta_data")
    @Embedded
    @Valid
    private ProductMeta meta;

    @JsonProperty("pricing_information")
    @Embedded
    @Valid
    private ProductPricingInformation pricingInformation;

    @JsonProperty("product_description")
    @Embedded
    @Valid
    private ProductDescription productDescription;

    {
        meta = new ProductMeta();
        pricingInformation = new ProductPricingInformation();
        productDescription = new ProductDescription();
    }
}
