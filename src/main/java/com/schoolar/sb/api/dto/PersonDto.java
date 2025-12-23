package com.schoolar.sb.api.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PersonDto {
    private Integer id;
    private String name;
    private DepartmentDto department;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
