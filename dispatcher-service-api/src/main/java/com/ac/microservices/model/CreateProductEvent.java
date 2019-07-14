package com.ac.microservices.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString(callSuper = true)
@Entity
@DiscriminatorValue("CreateProductEvent")
@JsonDeserialize(using = ProductDeserializer.class)
public class CreateProductEvent extends Event {

    @NotBlank(message = "Id is mandatory")
    @JsonProperty("id")
    @Column(name = "product_id")
    private String productId;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Model Number is mandatory")
    @JsonProperty("model_number")
    private String modelNumber;

    @JsonProperty("product_type")
    private String productType;

    @JsonProperty("meta_data")
    @Embedded
    private ProductMeta meta;

    @JsonProperty("pricing_information")
    @Embedded
    private ProductPricingInformation pricingInformation;

    @JsonProperty("product_description")
    @Embedded
    private ProductDescription productDescription;

}

//{
//        "id": "CG7088",
//        "name": "Nite Jogger Shoes",
//        "model_number": "BTO93",
//        "product_type": "inline",
//        "meta_data": {
//        "page_title": "adidas Nite Jogger Shoes - Black | adidas UK",
//        "site_name": "adidas United Kingdom",
//        "description": "Shop for Nite Jogger Shoes - Black at adidas.co.uk! See all the styles
//        and colours of Nite Jogger Shoes - Black at the official adidas UK online store.",
//        "keywords": "Nite Jogger Shoes",
//        "canonical": "//www.adidas.co.uk/nite-jogger-shoes/CG7088.html"
//        },
//        "pricing_information": {
//        "standard_price": 119.95,
//        "standard_price_no_vat": 99.96,
//        "currentPrice": 119.95
//        },
//        "product_description": {
//        "title": "Nite Jogger Shoes",
//        "subtitle": "Modern cushioning updates this flashy '80s standout.",
//        "text": "Inspired by the 1980 Nite Jogger, these shoes shine bright with retro style and
//        reflective details. The mesh and nylon ripstop upper is detailed with suede overlays. An
//        updated Boost midsole adds responsive cushioning for all-day comfort."
//        }
//        }
