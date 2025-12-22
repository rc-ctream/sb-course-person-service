package com.schoolar.sb.persistent;

import com.schoolar.sb.service.IdService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

@Slf4j
@Component
@RequiredArgsConstructor
public class DepartmentRepository {

    private final Map<Long, Department> indexedDepartmentDB = new HashMap<>();
    private final Map<DepartmentType, Department> departmentDB = new HashMap<>();
    private final IdService idService;


    @EventListener( ApplicationReadyEvent.class )
    public void initDepartments() {
        Stream.of( DepartmentType.values() )
                .map( type -> {
                    var department = new Department();
                    department.setType( type );
                    department.setDescription( type.getDescription() );
                    return department;
                } )
                .forEach( this::save );
    }

    public Department save( Department department ) {
        Long createdId = idService.createId();

        department.setId( createdId );
        department.setCreatedAt( LocalDateTime.now() );

        saveInDB( department );

        return department;
    }

    private void saveInDB( Department department ) {
        departmentDB.put( department.getType(), department );
        indexedDepartmentDB.put( department.getId(), department );
    }

    public Optional<Department> findByType( DepartmentType departmentType ) {
        return Optional.of( departmentDB.get( departmentType ) );
    }
}