package com.ac.microservices.product.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    private String id;

    @Column(name = "name")
    private String name;

    @Builder
    public Product(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
