package com.schoolar.sb_basics.api;

import com.schoolar.sb_basics.config.PersonProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping( "/persons" )
@RequiredArgsConstructor
public class PersonRestController {
    private final PersonProperties personProperties;

    @PostMapping
    public ResponseEntity<Void> createPerson() {
        System.out.println( "API_KEY: " + personProperties.getApi().getKey() );
        System.out.println( "URL: " + personProperties.getApi().getUrl() );
        // HTTP STATUS 200 ohne BODY
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Void> getAllPersons() {
        // HTTP STATUS 200 ohne BODY
        return ResponseEntity.ok().build();
    }
}
