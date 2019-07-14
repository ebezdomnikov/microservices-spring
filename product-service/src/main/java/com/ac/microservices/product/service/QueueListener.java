package com.ac.microservices.product.service;

import com.ac.microservices.product.model.ProductDescription;
import com.ac.microservices.product.model.ProductMeta;
import com.ac.microservices.product.model.ProductPriceInformation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Component;
import com.ac.microservices.product.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ac.microservices.model.CreateProductEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import java.io.IOException;

@Component
@Slf4j
public class QueueListener {

    @Autowired
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = "product-command-queue")
    public void productWorker(Message message) {
        try {
            log.info("productWorker; accepted message: {}", message);
            Product saved = productService.save(toProduct(message));
            log.debug("productWorker; saved value: {}", saved);
        } catch (Exception e) {
            log.error("{}", e);
        }
    }

    private Product toProduct(Message message) throws IOException {
        CreateProductEvent productEvent = objectMapper.readValue(message.getBody(), CreateProductEvent.class);
        return Product.builder()
                .id(productEvent.getProductId())
                .name(productEvent.getName())
                .modelNumber(productEvent.getModelNumber())
                .productType(productEvent.getProductType())
                .priceInformation(ProductPriceInformation
                        .builder()
                        .standardPrice(productEvent.getPricingInformation().getStandardPrice())
                        .standardPriceNoVat(productEvent.getPricingInformation().getStandardPriceNoVat())
                        .currentPrice(productEvent.getPricingInformation().getCurrentPrice())
                        .build()
                )
                .meta(ProductMeta.builder()
                        .canonical(productEvent.getMeta().getCanonical())
                        .description(productEvent.getMeta().getDescription())
                        .keywords(productEvent.getMeta().getKeywords())
                        .pageTitle(productEvent.getMeta().getPageTitle())
                        .siteName(productEvent.getMeta().getSiteName())
                        .build()
                )
                .productDescription(ProductDescription.builder()
                        .title(productEvent.getProductDescription().getTitle())
                        .subtitle(productEvent.getProductDescription().getSubtitle())
                        .text(productEvent.getProductDescription().getText())
                        .build()
                )
                .build();
    }
}
