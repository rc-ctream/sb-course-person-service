package com.schoolar.sb.api.dto;

import com.schoolar.sb.persistent.entity.Department;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PersonDto {
    private Integer id;
    private String name;
    private Department department;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
