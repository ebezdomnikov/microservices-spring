package com.ac.microservices.product.client;

import org.springframework.http.ResponseEntity;
import com.ac.microservices.product.model.ProductDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public interface ProductResource {

    @GetMapping("get/{id}")
    public ResponseEntity<ProductDto> findOne(@PathVariable("id") String id);

    @GetMapping(value = "list")
    public ResponseEntity<List<ProductDto>> findAll();

}
