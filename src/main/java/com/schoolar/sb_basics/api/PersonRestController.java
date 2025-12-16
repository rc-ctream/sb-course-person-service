package com.schoolar.sb_basics.api;

import com.schoolar.sb_basics.api.mapper.PersonMapper;
import com.schoolar.sb_basics.config.PersonProperties;
import com.schoolar.sb_basics.persistent.Person;
import com.schoolar.sb_basics.persistent.PersonRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping( "/persons" )
@RequiredArgsConstructor
public class PersonRestController {
    private final PersonProperties personProperties;
    private final PersonRepository personRepository;
    private final PersonMapper personMapper;


    @PostMapping
    public ResponseEntity<Void> createPerson( @RequestBody @Valid PersonRequestDto personRequestDto ) {
        var newPerson = personMapper.fromDto( personRequestDto );
        personRepository.save( newPerson );
        return ResponseEntity.ok().build();
    }


    @GetMapping
    public ResponseEntity<Void> getAllPersons() {
        // HTTP STATUS 200 ohne BODY
        return ResponseEntity.ok().build();
    }

    // add new http GET endpoint, for get one person by id
}
