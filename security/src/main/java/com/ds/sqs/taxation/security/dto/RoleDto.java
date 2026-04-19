package com.ds.sqs.taxation.security.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RoleDto {

    @NotNull
    private Long id;
    private String code;
    private String name;
}
