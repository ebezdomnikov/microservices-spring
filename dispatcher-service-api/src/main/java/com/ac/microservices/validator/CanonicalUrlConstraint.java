package com.ac.microservices.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CanonicalUrlValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CanonicalUrlConstraint {
    String message() default "Invalid canonical url";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
