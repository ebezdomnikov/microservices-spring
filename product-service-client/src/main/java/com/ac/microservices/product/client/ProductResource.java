package com.ac.microservices.product.client;

import com.ac.microservices.product.model.ProductFilterDto;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import com.ac.microservices.product.model.ProductDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ProductResource {

    @GetMapping("get/{id}")
    public ResponseEntity<ProductDto> findOne(@PathVariable("id") String id);

    @GetMapping(value = "list")
    public ResponseEntity<List<ProductDto>> findAll(
            @ModelAttribute ProductFilterDto productFilterDto,
            Pageable pageable
    );
}
