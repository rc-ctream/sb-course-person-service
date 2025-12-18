package com.schoolar.sb.service;

import com.schoolar.sb.persistent.Department;
import com.schoolar.sb.persistent.DepartmentType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final IdService idService;

    public Department createDepartment( String name ) {
        var type = DepartmentType.valueOf( name );

        var department = new Department();
        department.setType( type );
        department.setDescription( "???" );
        department.setCreatedAt( LocalDateTime.now() );


        var id = idService.createPersonId();
        department.setId( id );

        return department;
    }
}
