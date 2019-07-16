package com.ac.microservices.product.service;

import com.ac.microservices.model.UpdateProductEvent;
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
import java.util.Optional;

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

    @RabbitListener(queues = "product-update-command-queue")
    public void productUpdateWorker(Message message) {
        try {
            log.info("productWorker; accepted message: {}", message);
            UpdateProductEvent productEvent = objectMapper.readValue(message.getBody(), UpdateProductEvent.class);
            Product product = productService.findOne(productEvent.getProductId());
            if (product == null) {
                throw new IllegalArgumentException("Product not found");
            }

            if (productEvent.getName() != null) {
                product.setName(productEvent.getName());
            }
            if (productEvent.getModelNumber() != null) {
                product.setName(productEvent.getModelNumber());
            }
            if (productEvent.getProductType() != null) {
                product.setName(productEvent.getProductType());
            }

            if (productEvent.getMeta() != null) {
                ProductMeta productMeta = product.getMeta();

                if (productEvent.getMeta().getSiteName() != null) {
                    productMeta.setSiteName(productEvent.getMeta().getSiteName());
                }

                if (productEvent.getMeta().getPageTitle() != null) {
                    productMeta.setPageTitle(productEvent.getMeta().getPageTitle());
                }

                if (productEvent.getMeta().getCanonical() != null) {
                    productMeta.setCanonical(productEvent.getMeta().getCanonical());
                }

                if (productEvent.getMeta().getDescription() != null) {
                    productMeta.setDescription(productEvent.getMeta().getDescription());
                }
                if (productEvent.getMeta().getKeywords() != null) {
                    productMeta.setKeywords(productEvent.getMeta().getKeywords());
                }

                product.setMeta(productMeta);
            }

            if (productEvent.getPricingInformation() != null) {
                ProductPriceInformation priceInformation = product.getPriceInformation();

                if (productEvent.getPricingInformation().getCurrentPrice() != null) {
                    priceInformation.setCurrentPrice(productEvent.getPricingInformation().getCurrentPrice());
                }
                if (productEvent.getPricingInformation().getStandardPrice() != null) {
                    priceInformation.setStandardPrice(productEvent.getPricingInformation().getStandardPrice());
                }
                if (productEvent.getPricingInformation().getStandardPriceNoVat() != null) {
                    priceInformation.setStandardPriceNoVat(productEvent.getPricingInformation().getStandardPriceNoVat());
                }
                product.setPriceInformation(priceInformation);
            }

            if (productEvent.getProductDescription() != null) {
                ProductDescription productDescription = product.getProductDescription();

                if (productEvent.getProductDescription().getTitle() != null) {
                    productDescription.setTitle(productEvent.getProductDescription().getTitle());
                }
                if (productEvent.getProductDescription().getSubtitle() != null) {
                    productDescription.setSubtitle(productEvent.getProductDescription().getSubtitle());
                }
                if (productEvent.getProductDescription().getText() != null) {
                    productDescription.setText(productEvent.getProductDescription().getText());
                }

                product.setProductDescription(productDescription);
            }

            Product updated = productService.save(product);

            log.debug("productWorker; updated value: {}", updated);
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
