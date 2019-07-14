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

        ProductMeta productMeta = product.getMeta();
        ProductPriceInformation priceInformation = product.getPriceInformation();
        ProductDescription productDescription = product.getProductDescription();

        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .modelNumber(product.getModelNumber())
                .productType(product.getProductType())
                .priceInformation(ProductPriceInformationDto
                        .builder()
                        .standardPrice(priceInformation.getStandardPrice())
                        .standardPriceNoVat(priceInformation.getStandardPriceNoVat())
                        .currentPrice(priceInformation.getCurrentPrice())
                        .build()
                )
                .meta(ProductMetaDto.builder()
                        .canonical(productMeta.getCanonical())
                        .description(productMeta.getDescription())
                        .keywords(productMeta.getKeywords())
                        .pageTitle(productMeta.getPageTitle())
                        .siteName(productMeta.getSiteName())
                        .build()
                )
                .productDescription(ProductDescriptionDto.builder()
                        .title(productDescription.getTitle())
                        .subtitle(productDescription.getSubtitle())
                        .text(productDescription.getText())
                        .build()
                )
                .build();
    }

    @Override
    public Product doBackward(ProductDto productDto) {
        if (isNull(productDto)) {
            return null;
        }

        ProductMetaDto productMeta = productDto.getMeta();
        ProductPriceInformationDto priceInformation = productDto.getPriceInformation();
        ProductDescriptionDto productDescription = productDto.getProductDescription();

        return Product.builder()
                .id(productDto.getId())
                .name(productDto.getName())
                .modelNumber(productDto.getModelNumber())
                .productType(productDto.getProductType())
                .priceInformation(ProductPriceInformation
                        .builder()
                        .standardPrice(priceInformation.getStandardPrice())
                        .standardPriceNoVat(priceInformation.getStandardPriceNoVat())
                        .currentPrice(priceInformation.getCurrentPrice())
                        .build()
                )
                .meta(ProductMeta.builder()
                        .canonical(productMeta.getCanonical())
                        .description(productMeta.getDescription())
                        .keywords(productMeta.getKeywords())
                        .pageTitle(productMeta.getPageTitle())
                        .siteName(productMeta.getSiteName())
                        .build()
                )
                .productDescription(ProductDescription.builder()
                        .title(productDescription.getTitle())
                        .subtitle(productDescription.getSubtitle())
                        .text(productDescription.getText())
                        .build()
                )
                .build();
    }
}
