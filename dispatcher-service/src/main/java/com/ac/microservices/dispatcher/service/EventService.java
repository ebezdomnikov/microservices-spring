package com.ac.microservices.dispatcher.service;

import com.ac.microservices.dispatcher.repository.CreateProductEventRepository;
import com.ac.microservices.model.CreateProductEvent;
import com.ac.microservices.model.Event;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
@Transactional
public class EventService {

    @Autowired
    private CreateProductEventRepository createProductEventRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public void createProductEvent(CreateProductEvent event) {
        CreateProductEvent created = createProductEventRepository.save(event);
        log.debug("createProductEvent; created: {}", created);
        sendEventToQueue("product", created);
    }

    private void sendEventToQueue(String routingKey, Event event) {
        rabbitTemplate.convertAndSend(routingKey, event);
    }
}
