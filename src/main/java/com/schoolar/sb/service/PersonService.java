package com.schoolar.sb.service;

import com.schoolar.sb.api.PersonRequestDto;
import com.schoolar.sb.api.dto.PersonDto;
import com.schoolar.sb.api.mapper.PersonMapper;
import com.schoolar.sb.persistent.DepartmentType;
import com.schoolar.sb.persistent.PersonRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;
    private final PersonMapper personMapper;
    private final DepartmentService departmentService;

    public Long createPerson( @NotNull PersonRequestDto personRequestDto ) {
        var currentPerson = personMapper.fromDto( personRequestDto );
        var department = departmentService.getDepartment( personRequestDto.getDepartment() );
        currentPerson.setDepartment( department );
        return personRepository.save( currentPerson );
    }

    public List<PersonDto> getAllPersons() {
        return personRepository.findAll()
                .stream()
                .map( personMapper::toTdo )
                .toList();
    }

    public PersonDto getPersonById( Long personId ) {
        return personMapper.toTdo( personRepository.findByPersonId( personId ) );
    }

    public void updatePerson( Long personId, @NotBlank String name, DepartmentType departmentType ) {
        var currentPerson = personRepository.findByPersonId( personId );
        currentPerson.setName( name );

        var newDepartment = departmentService.getDepartment( departmentType );
        currentPerson.setDepartment( newDepartment );

        personRepository.update( currentPerson );
    }
}
