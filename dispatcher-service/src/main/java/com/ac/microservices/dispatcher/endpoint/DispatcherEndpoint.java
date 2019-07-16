package com.ac.microservices.dispatcher.endpoint;

import com.ac.microservices.dispatcher.service.EventService;
import com.ac.microservices.model.CreateProductEvent;
import com.ac.microservices.model.Event;
import com.ac.microservices.model.UpdateProductEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class DispatcherEndpoint {

    @Autowired
    private EventService eventService;

    @PostMapping("createProduct")
    public ResponseEntity createProduct(@Validated(Event.ValidationCreate.class) @RequestBody CreateProductEvent event) {
        eventService.createProductEvent(event);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("updateProduct")
    public ResponseEntity updateProduct(@Validated(Event.ValidationUpdate.class) @RequestBody UpdateProductEvent event) {
        eventService.updateProductEvent(event);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
