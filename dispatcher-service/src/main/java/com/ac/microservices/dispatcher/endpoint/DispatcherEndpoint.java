package com.ac.microservices.dispatcher.endpoint;

import com.ac.microservices.dispatcher.service.EventService;
import com.ac.microservices.model.CreateProductEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DispatcherEndpoint {

    @Autowired
    private EventService eventService;

    @PostMapping("createProduct")
    public ResponseEntity createProduct(@RequestBody CreateProductEvent event) {
        eventService.createProductEvent(event);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
