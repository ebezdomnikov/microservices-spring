package com.ac.microservices.product.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProductDescription {

    @Column(name = "descriptionTitle", length = 255)
    private String title;

    @Column(name = "descriptionSubtitle", length = 255)
    private String subtitle;

    @Column(name = "descriptionText", length = 2196)
    private String text;

}
