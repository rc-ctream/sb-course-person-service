package com.schoolar.sb.persistent;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Person {
    private Long id;
    private String name;
    private Department department;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
