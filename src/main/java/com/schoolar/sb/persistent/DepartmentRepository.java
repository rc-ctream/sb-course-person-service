package com.schoolar.sb.persistent;

import com.schoolar.sb.exception.DepartmentException;
import com.schoolar.sb.service.IdService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DepartmentRepository {

    private final Map<Long, Department> indexedDepartmentDB = new HashMap<>();
    private final Map<DepartmentType, Department> departmentDB = new HashMap<>();
    private final IdService idService;

    public Department save(Department department) {
        Long createdId = idService.createId();

        department.setId(createdId);
        department.setCreatedAt(LocalDateTime.now());

        saveInDB(department);

        return department;
    }

    private void saveInDB(Department department) {
        departmentDB.put(department.getType(), department);
        indexedDepartmentDB.put(department.getId(), department);
    }

    public Department findById(Long id) {
        if(!indexedDepartmentDB.containsKey(id))
            throw new DepartmentException("Could not find any stored department by id: " + id);

        return indexedDepartmentDB.get(id);
    }

    public Optional<Department> findByType(DepartmentType departmentType) {
        if(!departmentDB.containsKey(departmentType))
            return Optional.empty();

        return Optional.of(departmentDB.get(departmentType));
    }
}