package com.ac.microservices.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.ZonedDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString(callSuper = true)
@Entity
@DiscriminatorValue("CreateProductEvent")
public class CreateProductEvent extends Event {

    private String name;
}
