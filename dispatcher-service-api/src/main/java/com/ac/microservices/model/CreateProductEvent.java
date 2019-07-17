package com.ac.microservices.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString(callSuper = true)
@Entity
@Builder
@DiscriminatorValue("CreateProductEvent")
@JsonDeserialize(using = ProductDeserializer.class)
public class CreateProductEvent extends Event {

    @NotBlank(message = "Id is mandatory", groups = {Event.ValidationCreate.class})
    @Length(min = 1, max = 255, groups = {Event.ValidationCreate.class})
    @JsonProperty("id")
    @Column(name = "product_id")
    private String productId;

    @NotBlank(message = "Name is mandatory", groups = {Event.ValidationCreate.class})
    @Length(min = 1, max = 255, groups = {Event.ValidationCreate.class, Event.ValidationUpdate.class})
    private String name;

    @NotBlank(message = "Model Number is mandatory", groups = {Event.ValidationCreate.class})
    @Length(min = 1, max = 255, groups = {Event.ValidationCreate.class, Event.ValidationUpdate.class})
    @JsonProperty("model_number")
    private String modelNumber;

    @NotBlank(message = "Product Type is mandatory", groups = {Event.ValidationCreate.class})
    @Length(min = 1, max = 255, groups = {Event.ValidationCreate.class, Event.ValidationUpdate.class})
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

