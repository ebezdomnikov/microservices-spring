package com.ac.microservices.product.repository;

import com.ac.microservices.product.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product,String> {
}
