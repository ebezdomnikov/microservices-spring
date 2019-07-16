package com.ac.microservices.product.endpoint;

import com.ac.microservices.product.client.ProductResource;
import com.ac.microservices.product.model.*;
import com.ac.microservices.product.service.ProductService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class ProductEndpoint implements ProductResource {

    @Autowired
    private ProductService service;

    @Autowired
    private ProductConverter converter;

    @Override
    public ResponseEntity<ProductDto> findOne(@PathVariable("id") String id) {
        return ResponseEntity.ok(converter.doForward(service.findOne(id)));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", dataType = "string", paramType = "query",
                    value = "List of ids of products to filter"),
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page."),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")
    })
    @Override
    public ResponseEntity<List<ProductDto>> findAll(
            @ModelAttribute ProductFilterDto productFilterDto,
            Pageable pageable
    ) {
        ProductSpecification productSpecification = new ProductSpecification(productFilterDto);

        Page<Product> products  = service.findAll(productSpecification, pageable);

        List<ProductDto> result = new ArrayList<>();

        for (Product product : products.getContent()) {
            result.add(converter.doForward(product));
        }

        log.info("Product End Point; count: {}", products.getTotalElements());
        log.info("Product End Point; page: {}", products.getTotalPages());

        return ResponseEntity.ok(result);
    }

}
