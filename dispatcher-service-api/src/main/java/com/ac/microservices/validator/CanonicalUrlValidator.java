package com.ac.microservices.validator;

import org.hibernate.annotations.common.annotationfactory.AnnotationDescriptor;
import org.hibernate.annotations.common.annotationfactory.AnnotationFactory;
import org.hibernate.validator.constraints.URL;
import org.hibernate.validator.internal.constraintvalidators.hv.URLValidator;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CanonicalUrlValidator implements ConstraintValidator<CanonicalUrlConstraint, String> {

    private static final String PROTOCOL_PREFIX = "http:";

    @Override
    public void initialize(CanonicalUrlConstraint canonical) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext cxt) {

        if (value == null) {
            return false;
        }
        if ((value.startsWith("//")) && (value.indexOf("://") == -1)) {
            value = PROTOCOL_PREFIX + value;
        }

        AnnotationDescriptor descriptor = new AnnotationDescriptor( URL.class );
        URL url = AnnotationFactory.create(descriptor);

        URLValidator urlValidator = new URLValidator();
        urlValidator.initialize(url);

        return urlValidator.isValid(value, cxt);
    }

}
