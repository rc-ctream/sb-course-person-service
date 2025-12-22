package com.schoolar.sb.service;

import com.schoolar.sb.exception.DepartmentException;
import com.schoolar.sb.persistent.Department;
import com.schoolar.sb.persistent.DepartmentRepository;
import com.schoolar.sb.persistent.DepartmentType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepo;

    public Department createDepartment( DepartmentType departmentType ) {
        var department = new Department();
        department.setType( departmentType );
        department.setDescription( departmentType.getDescription() );
        return departmentRepo.save( department );
    }

    public Department getDepartment( DepartmentType departmentType ) {
        return departmentRepo
                .findByType( departmentType )
                .orElseThrow( () -> new DepartmentException( "Could not find department by type: " + departmentType ) );
    }
}
