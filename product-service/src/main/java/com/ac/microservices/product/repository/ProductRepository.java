package com.ac.microservices.product.repository;

import com.ac.microservices.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductRepository extends JpaRepository<Product,String>, JpaSpecificationExecutor<Product> {
}
