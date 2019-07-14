package com.ac.microservices.product.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Getter
@Setter
@Builder
public class ProductMeta {

    @Column(name = "metaSiteName")
    private String siteName;

    @Column(name = "metaDescription")
    private String description;

    @Column(name = "metaKeywords")
    private String keywords;

    @Column(name = "metaCanonical")
    private String canonical;

    @Column(name = "metaPageTitle")
    private String pageTitle;
}
