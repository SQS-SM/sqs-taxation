package com.ds.sqs.taxation.security.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class CompanyDto {

    @NotNull
    private Long id;
    private UUID guid;

    private Long organisationId;

    private String name;
    private String registeredName;
    private String tradingName;
    private String natureOfBusiness;
    private String companyRegistrationNumber;
    private String taxNumber;

    private Long physicalAddressId;
    private Long postalAddressId;
    private Long bankDetailsId;
}