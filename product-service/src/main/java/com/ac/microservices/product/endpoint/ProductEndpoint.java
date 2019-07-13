package com.ac.microservices.product.endpoint;

import com.ac.microservices.product.client.ProductResource;
import com.ac.microservices.product.model.Product;
import com.ac.microservices.product.model.ProductConverter;
import com.ac.microservices.product.model.ProductDto;
import com.ac.microservices.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
public class ProductEndpoint implements ProductResource {

    @Autowired
    private ProductService service;

    @Autowired
    private ProductConverter converter;

    @Override
    public ResponseEntity<ProductDto> findOne(@PathVariable("id") String id) {
        return ResponseEntity.ok(converter.doForward(service.findOne(id)));
    }

    @Override
    public ResponseEntity<List<ProductDto>> findAll() {

        List<ProductDto> result = new ArrayList<>();
        for (Product product : service.findAll()) {
            result.add(converter.doForward(product));
        }
        return ResponseEntity.ok(result);
    }

}
