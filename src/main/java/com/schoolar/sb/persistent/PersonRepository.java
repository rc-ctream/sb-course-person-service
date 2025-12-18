package com.schoolar.sb.persistent;


import com.schoolar.sb.exception.PersonException;
import com.schoolar.sb.service.IdService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class PersonRepository {

    private static final Map<Integer, Person> PERSONS_DB = new HashMap<>();
    private final IdService idService;

    public Integer save( Person person ) {
        var personId = idService.createPersonId();

        person.setId( personId );
        person.setCreatedAt( LocalDateTime.now() );

        PERSONS_DB.put( personId, person );

        return personId;
    }

    public List<Person> findAll() {
        return List.copyOf( PERSONS_DB.values() );
    }

    public Person findByPersonId( Integer personId ) {
        var person = PERSONS_DB.get( personId );
        if ( Objects.isNull( person ) ) {
            throw new PersonException( "Person with id " + personId + " not found" );
        }
        return person;
    }
}
