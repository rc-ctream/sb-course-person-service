package com.schoolar.sb.service;

import com.schoolar.sb.persistent.Department;
import com.schoolar.sb.persistent.DepartmentRepository;
import com.schoolar.sb.persistent.DepartmentType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepo;

    public Department createDepartment( DepartmentType departmentType ) {
        var department = new Department();
        department.setType( departmentType );
        department.setDescription(departmentType.getDescription());
        return departmentRepo.save(department);
    }

    public Optional<Department> getDepartment(DepartmentType departmentType) {
        return departmentRepo.findByType(departmentType);
    }
}
