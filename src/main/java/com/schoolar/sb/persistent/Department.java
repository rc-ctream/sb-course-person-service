package com.schoolar.sb.persistent;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Department {
    private Long id;
    private DepartmentType type;
    private String description;
    private LocalDateTime createdAt;
}
