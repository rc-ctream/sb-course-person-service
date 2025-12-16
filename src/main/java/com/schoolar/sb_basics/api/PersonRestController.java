package com.schoolar.sb_basics.api;

import com.schoolar.sb_basics.config.PersonProperties;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping( "/persons" )
@RequiredArgsConstructor
public class PersonRestController {
    private final PersonProperties personProperties;

    @PostMapping
    public ResponseEntity<Void> createPerson( @RequestBody @Valid PersonRequestDto person ) {
        // HTTP STATUS 200 ohne BODY
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Void> getAllPersons() {
        // HTTP STATUS 200 ohne BODY
        return ResponseEntity.ok().build();
    }
}
