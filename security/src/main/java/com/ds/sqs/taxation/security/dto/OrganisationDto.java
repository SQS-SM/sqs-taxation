package com.ds.sqs.taxation.security.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class OrganisationDto {

    private Long id;
    private UUID guid;
    private String name;
}
