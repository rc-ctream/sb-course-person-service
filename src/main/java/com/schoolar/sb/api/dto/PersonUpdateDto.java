package com.schoolar.sb.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PersonUpdateDto {
    @NotBlank
    private String name;
    private String department;
}
