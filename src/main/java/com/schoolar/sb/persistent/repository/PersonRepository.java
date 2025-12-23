package com.schoolar.sb.persistent.repository;


import com.schoolar.sb.exception.PersonException;
import com.schoolar.sb.persistent.entity.DepartmentType;
import com.schoolar.sb.persistent.entity.Person;
import com.schoolar.sb.service.IdService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class PersonRepository {

    private static final Map<Long, Person> PERSONS_DB = new HashMap<>();
    private final IdService idService;


    @CacheEvict( value = "allPersons", allEntries = true )
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

    @Cacheable( value = "allPersons" )
    public List<Person> findAll() {
        log.info( "Load all persons from db" );
        return List.copyOf( PERSONS_DB.values() );
    }

    @Cacheable( value = "personById", key = "#personId" )
    public Person findByPersonId( Long personId ) {
        log.info( "Read person by id from db" );
        var person = PERSONS_DB.get( personId );
        if ( Objects.isNull( person ) ) {
            throw new PersonException( "Person with id " + personId + " not found" );
        }
        return person;
    }

    @CacheEvict( value = { "allPersons", "personById" }, key = "#personId", allEntries = true )
    public void deleteUserById( Long personId ) {
        PERSONS_DB.remove( personId );
    }

    public Optional<Person> findByNameAndDepartment( String name, DepartmentType department ) {
        return PERSONS_DB.values()
                .stream()
                .filter( person ->
                        person.getName().equals( name )
                        && person.getDepartment().getType().equals( department )
                )
                .findFirst();
    }
}
