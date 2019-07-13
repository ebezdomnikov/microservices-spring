package com.ac.microservices.product.model;

import com.google.common.base.Converter;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class ProductConverter extends Converter<Product, ProductDto> {
    @Override
    public ProductDto doForward(Product product) {
        if (isNull(product)) {
            return null;
        }
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .build();
    }

    @Override
    public Product doBackward(ProductDto productDto) {
        if (isNull(productDto)) {
            return null;
        }
        return Product.builder()
                .id(productDto.getId())
                .name(productDto.getName())
                .build();
    }
}
