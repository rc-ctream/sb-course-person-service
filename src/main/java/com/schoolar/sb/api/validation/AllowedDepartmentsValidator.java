package com.schoolar.sb.api.validation;

import com.schoolar.sb.persistent.DepartmentType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class AllowedDepartmentsValidator implements ConstraintValidator<AllowedDepartments, DepartmentType> {

    private static final Set<DepartmentType> ALLOWED_DEPARTMENTS = new HashSet<>();

    @Override
    public void initialize( AllowedDepartments constraintAnnotation ) {
        ALLOWED_DEPARTMENTS.addAll(Arrays.asList(DepartmentType.values()));
    }

    @Override
    public boolean isValid(DepartmentType departmentType, ConstraintValidatorContext constraintValidatorContext) {
        return departmentType != null && ALLOWED_DEPARTMENTS.contains(departmentType);
    }
}
