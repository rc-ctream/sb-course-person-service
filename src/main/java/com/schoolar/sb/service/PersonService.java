package com.schoolar.sb.service;

import com.schoolar.sb.api.PersonRequestDto;
import com.schoolar.sb.api.dto.PersonDto;
import com.schoolar.sb.api.mapper.PersonMapper;
import com.schoolar.sb.persistent.Department;
import com.schoolar.sb.persistent.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;
    private final PersonMapper personMapper;
    private final DepartmentService departmentService;

    public Long createPerson( PersonRequestDto personRequestDto ) {
        var currentPerson = personMapper.fromDto( personRequestDto );

        Optional<Department> optDepartment = departmentService.getDepartment(personRequestDto.getDepartment());
        var department = optDepartment.orElseGet(()-> departmentService.createDepartment(personRequestDto.getDepartment()));

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
}
