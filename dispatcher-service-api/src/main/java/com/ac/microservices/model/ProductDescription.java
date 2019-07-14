package com.ac.microservices.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Embeddable;

@Getter
@Setter
@Embeddable
public class ProductDescription {

    @NotBlank(message = "Title is mandatory")
    @Length(max = 255)
    private String title;

    @NotBlank(message = "Subtitle is mandatory")
    @Length(max = 255)
    private String subtitle;

    @NotBlank(message = "Text is mandatory")
    @Length(max = 2196)
    private String text;

}
