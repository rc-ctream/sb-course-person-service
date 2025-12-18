package com.schoolar.sb.api;

import com.schoolar.sb.api.validation.AllowedDepartments;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PersonRequestDto {

    @NotBlank
    private String name;

    // TODO: use enum
    @AllowedDepartments
    private String department;
}
