package com.ds.sqs.taxation.employee.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class EmployeeBenefitDto {

    private Long id;
    private UUID guid;
    private Long companyId;
    private String employeeNumber;
    private String firstName;
    private String lastName;
    private String employmentType;
    private BigDecimal basicSalary;
    private Long bankDetailsId;
    private Long physicalAddressId;
    private Long postalAddressId;
}
