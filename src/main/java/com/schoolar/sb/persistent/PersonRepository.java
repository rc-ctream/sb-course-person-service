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

    private static final Map<Long, Person> PERSONS_DB = new HashMap<>();
    private final IdService idService;

    public Long save( Person person ) {
        var personId = idService.createId();

        person.setId( personId );
        person.setCreatedAt( LocalDateTime.now() );

        PERSONS_DB.put( personId, person );

        return personId;
    }

    public void update( Person person ) {
        person.setUpdatedAt( LocalDateTime.now() );
        PERSONS_DB.put( person.getId(), person );
    }

    public List<Person> findAll() {
        return List.copyOf( PERSONS_DB.values() );
    }

    public Person findByPersonId( Long personId ) {
        var person = PERSONS_DB.get( personId );
        if ( Objects.isNull( person ) ) {
            throw new PersonException( "Person with id " + personId + " not found" );
        }
        return person;
    }

    public void deleteUserById( Long personId ) {
        PERSONS_DB.remove( personId );
    }
}
