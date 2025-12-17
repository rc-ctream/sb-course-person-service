package com.schoolar.sb.persistent;


import com.schoolar.sb.exception.PersonException;
import com.schoolar.sb.service.IdService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class PersonRepository {

    private static final Map<Integer, Person> PERSONS_DB = new HashMap<>();
    private final IdService idService;

    public void save( Person person ) {
        var personId = idService.createPersonId();
        log.info( "Generated person id: {}", personId );
        log.info( "Saving person: {}", person );

        person.setId( personId );
        person.setCreatedAt( LocalDateTime.now() );

        PERSONS_DB.put( personId, person );
    }

    // get person by unique id. if id is not given, then throw exception

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
