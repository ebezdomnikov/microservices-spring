package com.ac.microservices.product.client;

import org.springframework.cloud.netflix.feign.FeignClient;

import static com.ac.microservices.product.client.ProductClient.SERVICE_ID;

@FeignClient(SERVICE_ID)
public interface ProductClient extends ProductResource {
    String SERVICE_ID = "product-service";
}
