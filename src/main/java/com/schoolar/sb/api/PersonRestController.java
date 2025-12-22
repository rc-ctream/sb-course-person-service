package com.schoolar.sb.api;

import com.schoolar.sb.api.dto.PersonDto;
import com.schoolar.sb.api.mapper.PersonMapper;
import com.schoolar.sb.persistent.PersonRepository;
import com.schoolar.sb.service.PersonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping( "/persons" )
public class PersonRestController {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;
    private final PersonService personService;

    @PostMapping
    public ResponseEntity<Void> createPerson( @RequestBody @Valid PersonRequestDto personRequestDto ) {
        var newPerson = personMapper.fromDto( personRequestDto );
        personRepository.save( newPerson );
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<PersonDto>> getAllPersons() {
        var persons = personRepository.findAll()
                .stream()
                .map( personMapper::toTdo )
                .toList();
        return ResponseEntity.ok( persons );
    }

    @GetMapping( "/{personId}" )
    public ResponseEntity<PersonDto> getPersonById( @PathVariable Long personId ) {

        var person = personRepository.findByPersonId( personId );
        var personDto = personMapper.toTdo( person );

        return ResponseEntity.ok( personDto );
    }

    @PutMapping( "/{personId}" )
    public ResponseEntity<Void> updatePerson( @PathVariable Long personId, @RequestBody PersonUpdateDto personUpdateDto ) {
        personService.updatePersonName( personId, personUpdateDto.getName() );
        return ResponseEntity.ok().build();
    }

}
