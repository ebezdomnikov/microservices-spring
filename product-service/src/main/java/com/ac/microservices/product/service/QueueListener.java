package com.ac.microservices.product.service;

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
    private ProductService customerService;

    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = "product-command-queue")
    public void productWorker(Message message) {
        try {
            log.info("customerWorker; accepted message: {}", message);
            Product saved = customerService.save(toProduct(message));
            log.debug("customerWorker; saved value: {}", saved);
        } catch (Exception e) {
            log.error("{}", e);
        }
    }

    private Product toProduct(Message message) throws IOException {
        CreateProductEvent productEvent = objectMapper.readValue(message.getBody(), CreateProductEvent.class);
        return Product.builder()
                .name(productEvent.getName())
                .build();
    }
}
