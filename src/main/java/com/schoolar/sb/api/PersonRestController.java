package com.schoolar.sb.api;

import com.schoolar.sb.api.mapper.PersonMapper;
import com.schoolar.sb.config.PersonProperties;
import com.schoolar.sb.persistent.PersonRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping( "/persons" )
public class PersonRestController {

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
