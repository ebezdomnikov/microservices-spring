package com.ac.microservices.model;

import com.ac.microservices.validator.CanonicalUrlValidator;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;
import org.junit.Assert;
import org.junit.Test;

import javax.validation.*;
import java.util.Set;

public class CreateProductEventValidationTest {

    private static Validator validator;
    static {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.usingContext().getValidator();
    }

    @Test
    public void testValidators_create() {

        ProductMeta productMeta = new ProductMeta();
        productMeta.setSiteName("site name");
        productMeta.setCanonical("invalid url");

        CreateProductEvent event = new CreateProductEvent();
        event.setName("name");
        event.setProductType("inline");
        event.setModelNumber("model number");

        Set<ConstraintViolation<CreateProductEvent>> validates = validator.validate(event, Event.ValidationCreate.class);
        Assert.assertTrue(validates.size() == 9);
        validates.stream().map(v -> v.getMessage())
                .forEach(System.out::println);
    }

    @Test
    public void testValidators_update() {

        ProductMeta productMeta = new ProductMeta();
        productMeta.setSiteName("site name");
        productMeta.setCanonical("googlejp[097-0com@");

        UpdateProductEvent event = new UpdateProductEvent();
        event.setName("name");
        event.setProductType("inline");
        event.setModelNumber("model number");
        event.setMeta(productMeta);
        ProductDescription productDescription = new ProductDescription();
        productDescription.setTitle("12fasdfasdfasd12fasdfasdfasd12fasdfasdfasd12fasdfasdfasd12fasdfasdfasd12fasdfasdfasd12fasdfasdfasd12fasdfasdfasd12fasdfasdfasd12fasdfasdfasd12fasdfasdfasd12fasdfasdfasd12fasdfasdfasd12fasdfasdfasd12fasdfasdfasd12fasdfasdfasd12fasdfasdfasd12fasdfasdfasd12fasdfasdfasd12fasdfasdfasd12fasdfasdfasd12fasdfasdfasd12fasdfasdfasd12fasdfasdfasd12fasdfasdfasd12fasdfasdfasd12fasdfasdfasd12fasdfasdfasd12fasdfasdfasd");
        event.setProductDescription(productDescription);

        Set<ConstraintViolation<UpdateProductEvent>> validates = validator.validate(event, Event.ValidationUpdate.class);
        Assert.assertTrue(validates.size() == 4);
        validates.stream().map(v -> v.getMessage())
                .forEach(System.out::println);
    }
}
