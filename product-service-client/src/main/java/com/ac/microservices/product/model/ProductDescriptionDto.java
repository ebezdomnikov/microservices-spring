package com.ac.microservices.product.model;

import lombok.*;

@Getter
@Setter
@Builder
public class ProductDescriptionDto {

    private String title;
    private String subtitle;
    private String text;
}
