package com.schoolar.sb.service;

import com.schoolar.sb.api.PersonRequestDto;
import com.schoolar.sb.api.dto.PersonDto;
import com.schoolar.sb.api.mapper.PersonMapper;
import com.schoolar.sb.persistent.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;
    private final PersonMapper personMapper;
    private final DepartmentService departmentService;

    public Integer createPerson( PersonRequestDto personRequestDto ) {
        var currentPerson = personMapper.fromDto( personRequestDto );

        var department = departmentService.createDepartment( personRequestDto.getDepartment() );
        currentPerson.setDepartment( department );

        return personRepository.save( currentPerson );
    }

    public List<PersonDto> getAllPersons() {
        return personRepository.findAll()
                .stream()
                .map( personMapper::toTdo )
                .toList();
    }

    public PersonDto getPersonById( Integer personId ) {
        return personMapper.toTdo( personRepository.findByPersonId( personId ) );
    }
}
