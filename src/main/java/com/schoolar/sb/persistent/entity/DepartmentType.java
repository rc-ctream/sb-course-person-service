package com.schoolar.sb.persistent.entity;

import lombok.Getter;

@Getter
public enum DepartmentType {
    DEV("Development"),
    MARKETING("Marketing"),
    TEST("QA");

    private final String description;

    DepartmentType(String description) {
        this.description = description;
    }
}
