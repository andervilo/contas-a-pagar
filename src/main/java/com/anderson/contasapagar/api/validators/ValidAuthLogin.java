package com.anderson.contasapagar.api.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = AuthLoginValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidAuthLogin {
    String message() default "Invalid AuthLogin data";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
