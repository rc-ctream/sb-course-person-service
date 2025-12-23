package com.schoolar.sb.api.mapper;

import com.schoolar.sb.api.dto.DepartmentDto;
import com.schoolar.sb.persistent.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper( componentModel = MappingConstants.ComponentModel.SPRING )
public interface DepartmentMapper {
    DepartmentDto toDto( Department department );
}
