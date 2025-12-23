package com.schoolar.sb.api.dto;

import com.schoolar.sb.persistent.entity.DepartmentType;
import lombok.Data;

@Data
public class DepartmentDto {
    private DepartmentType type;
}
