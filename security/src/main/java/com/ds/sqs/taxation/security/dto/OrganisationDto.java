package com.ds.sqs.taxation.security.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class OrganisationDto {

    @NotNull
    private Long id;
    private UUID guid;
    private String name;
}
