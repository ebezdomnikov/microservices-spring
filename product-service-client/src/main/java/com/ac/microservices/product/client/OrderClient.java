package com.ac.microservices.product.client;

import org.springframework.cloud.netflix.feign.FeignClient;

import static com.poc.microservices.order.client.OrderClient.SERVICE_ID;

/**
 * Created by Rustam_Kadyrov on 19.05.2017.
 */
@FeignClient(SERVICE_ID)
public interface OrderClient extends OrderResource {
    String SERVICE_ID = "order-service";
}
