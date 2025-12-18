package com.schoolar.sb.api.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.HashSet;
import java.util.Set;

public class AllowedDepartmentsValidator implements ConstraintValidator<AllowedDepartments, String> {

    // TODO: replace String with Enum
    private static final Set<String> ALLOWED_DEPARTMENTS = new HashSet<>();

    @Override
    public void initialize( AllowedDepartments constraintAnnotation ) {
        ALLOWED_DEPARTMENTS.add( "DEV" );
        ALLOWED_DEPARTMENTS.add( "TEST" );
        ALLOWED_DEPARTMENTS.add( "MARKETING" );
    }

    @Override
    public boolean isValid( String inputValue, ConstraintValidatorContext constraintValidatorContext ) {
        return inputValue != null && ALLOWED_DEPARTMENTS.contains( inputValue );
    }
}
