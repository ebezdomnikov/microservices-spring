package com.ac.microservices.product.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.MetaValue;

import javax.persistence.*;
import javax.validation.Valid;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "products")
public class Product {

    @Id
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "product_type")
    @JsonProperty("product_type")
    private String productType;

    @Column(name = "model_number")
    private String modelNumber;

    @Embedded
    @JsonProperty("meta_data")
    @Valid
    private ProductMeta meta = new ProductMeta();

    @Embedded
    @JsonProperty("pricing_information")
    @Valid
    private ProductPriceInformation priceInformation = new ProductPriceInformation();

    @Embedded
    @JsonProperty("product_description")
    @Valid
    private ProductDescription productDescription = new ProductDescription();
}
