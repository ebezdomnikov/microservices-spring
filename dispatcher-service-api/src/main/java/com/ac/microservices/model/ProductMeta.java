package com.ac.microservices.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Setter
@Embeddable
public class ProductMeta {

    @NotBlank(message = "Site name is mandatory")
    @JsonProperty("site_name")
    @Column(name = "metaSiteName")
    private String siteName;

    @NotBlank(message = "Description is mandatory")
    @JsonProperty("description")
    @Column(name = "metaDescription")
    private String description;

    @NotBlank(message = "Keywords is mandatory")
    @JsonProperty("keywords")
    @Column(name = "metaKeywords")
    private String keywords;

    @NotBlank(message = "Canonical is mandatory")
    @URL
    @JsonProperty("canonical")
    @Column(name = "metaCanonical")
    private String canonical;

    @NotBlank(message = "Page Title is mandatory")
    @JsonProperty("page_title")
    @Column(name = "metaPageTitle")
    private String pageTitle;
}
