package com.schoolar.sb.api.validation;

import com.schoolar.sb.api.PersonRequestDto;
import com.schoolar.sb.persistent.PersonRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ValidPersonValidator implements ConstraintValidator<ValidPerson, PersonRequestDto> {

    private final PersonRepository personRepository;

    @Override
    public boolean isValid( PersonRequestDto personRequestDto, ConstraintValidatorContext context ) {
        var opt = personRepository
                .findByNameAndDepartment( personRequestDto.getName(), personRequestDto.getDepartment() );

        return opt.isEmpty();
    }
}
