package com.ac.microservices.product.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.core.annotation.AliasFor;

@Getter
@Setter
public class ProductFilterDto {

    private String ids;
}

