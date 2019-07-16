package com.ac.microservices.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Embeddable
public class ProductDescription {
    @NotBlank(message = "Title is mandatory", groups = {Event.ValidationCreate.class})
    @Length(min = 1, max = 255, groups = {Event.ValidationCreate.class, UpdateProductEvent.ValidationUpdate.class})
    private String title;

    @NotBlank(message = "Subtitle is mandatory", groups = {Event.ValidationCreate.class})
    @Length(min = 1, max = 255, groups = {Event.ValidationCreate.class, UpdateProductEvent.ValidationUpdate.class})
    private String subtitle;

    @NotBlank(message = "Text is mandatory", groups = {Event.ValidationCreate.class})
    @Length(min = 1, max = 2196, groups = {Event.ValidationCreate.class, UpdateProductEvent.ValidationUpdate.class})
    private String text;

}
