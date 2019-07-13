package com.ac.microservices.product.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class ProductDto {

    private String id;
    private String name;
}
