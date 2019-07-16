package com.ac.microservices.product.service;

import com.ac.microservices.product.model.Product;
import com.ac.microservices.product.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Product save(Product product) {
        return repository.save(product);
    }

    public Product findOne(String id) {
        log.info("findOne; {}", id);
        return repository.findOne(id);
    }

    public Page<Product> findAll(Specification<Product> specification, Pageable pageable) {
        return repository.findAll(specification, pageable);
    }

}
