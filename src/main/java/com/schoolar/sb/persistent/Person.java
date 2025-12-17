package com.schoolar.sb.persistent;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Person {

    private Integer id;
    private String name;
    private String department;
    private LocalDateTime createdAt;
}
