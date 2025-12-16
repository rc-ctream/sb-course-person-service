package com.schoolar.sb_basics.api.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Constraint( validatedBy = AllowedDepartmentsValidator.class )
@Target( { java.lang.annotation.ElementType.FIELD } )
@Retention( java.lang.annotation.RetentionPolicy.RUNTIME )
public @interface AllowedDepartments {

    String message() default "Department not allowed";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String[] value() default {};
}
