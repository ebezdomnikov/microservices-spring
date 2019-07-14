package com.ac.microservices.product.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

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
    private ProductMeta meta;

    @Embedded
    @JsonProperty("pricing_information")
    private ProductPriceInformation priceInformation;

    @Embedded
    @JsonProperty("product_description")
    private ProductDescription productDescription;
}
