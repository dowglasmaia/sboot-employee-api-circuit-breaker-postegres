package com.dmaia.software.employeeapi.exeptions.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class FieldMessage {
    private String fieldName;
    private String message;
}
