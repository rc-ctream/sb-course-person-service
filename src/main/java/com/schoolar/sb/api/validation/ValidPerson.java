package com.schoolar.sb.api.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target( { ElementType.PARAMETER, ElementType.FIELD } )
@Retention( RetentionPolicy.RUNTIME )
@Constraint( validatedBy = ValidPersonValidator.class ) // Verknüpfung zur Logik
public @interface ValidPerson {

    String message() default "Person already exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
