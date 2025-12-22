package com.schoolar.sb.data;

import com.schoolar.sb.persistent.*;
import com.schoolar.sb.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final DepartmentService departmentService;
    private final PersonRepository personRepository;
    private final DepartmentRepository departmentRepository;

    @EventListener( ApplicationReadyEvent.class )
    public void initPersons() {

        Stream.of( DepartmentType.values() )
                .map( type -> {
                    var department = new Department();
                    department.setType( type );
                    department.setDescription( type.getDescription() );
                    return department;
                } )
                .forEach( departmentRepository::save );

        var jonas = new Person();
        jonas.setName( "Jonas" );
        jonas.setDepartment( departmentService.getDepartment( DepartmentType.DEV ) );

        var ramazan = new Person();
        ramazan.setName( "Ramazan" );
        ramazan.setDepartment( departmentService.getDepartment( DepartmentType.MARKETING ) );

        var silvia = new Person();
        silvia.setName( "Silvia" );
        silvia.setDepartment( departmentService.getDepartment( DepartmentType.TEST ) );

        Stream.of( jonas, ramazan, silvia )
                .forEach( personRepository::save );
    }


}
