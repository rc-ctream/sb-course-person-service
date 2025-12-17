package com.schoolar.sb.persistent;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Component
public class PersonRepository {

    private static final Map<Integer, Person> PERSONS_DB = new HashMap<>();
    private final AtomicInteger counter = new AtomicInteger( 0 );

    public void save( Person person ) {
        log.info( "Saving person: {}", person );
        PERSONS_DB.put( counter.getAndIncrement(), person );
    }

    // get person by unique id. if id is not given, then throw exception

    public List<Person> findAll() {
        return List.copyOf( PERSONS_DB.values() );
    }

}
