package com.ac.microservices.model;

import com.ac.microservices.validator.CanonicalUrlConstraint;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Embeddable
public class ProductMeta {

    @NotBlank(message = "Site name is mandatory", groups = {Event.ValidationCreate.class})
    @Length(min = 1, max = 255, groups = {Event.ValidationCreate.class, UpdateProductEvent.ValidationUpdate.class})
    @JsonProperty("site_name")
    @Column(name = "metaSiteName")
    private String siteName;

    @NotBlank(message = "Description is mandatory", groups = {Event.ValidationCreate.class})
    @Length(min = 1, max = 255, groups = {Event.ValidationCreate.class, UpdateProductEvent.ValidationUpdate.class})
    @JsonProperty("description")
    @Column(name = "metaDescription")
    private String description;

    @NotBlank(message = "Keywords is mandatory", groups = {Event.ValidationCreate.class})
    @Length(min = 1, max = 255, groups = {Event.ValidationCreate.class, UpdateProductEvent.ValidationUpdate.class})
    @JsonProperty("keywords")
    @Column(name = "metaKeywords")
    private String keywords;

    @NotBlank(message = "Canonical is mandatory", groups = {Event.ValidationCreate.class})
    @Length(min = 1, max = 255, groups = {Event.ValidationCreate.class, UpdateProductEvent.ValidationUpdate.class})
    @CanonicalUrlConstraint(groups = {Event.ValidationCreate.class, UpdateProductEvent.ValidationUpdate.class})
    @JsonProperty("canonical")
    @Column(name = "metaCanonical")
    private String canonical;

    @NotBlank(message = "Page Title is mandatory", groups = {Event.ValidationCreate.class})
    @Length(min = 1, max = 255, groups = {Event.ValidationCreate.class, UpdateProductEvent.ValidationUpdate.class})
    @JsonProperty("page_title")
    @Column(name = "metaPageTitle")
    private String pageTitle;
}
