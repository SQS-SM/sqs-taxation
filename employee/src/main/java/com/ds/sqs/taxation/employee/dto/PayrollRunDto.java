package com.ds.sqs.taxation.employee.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PayrollRunDto {

    private Long id;
    private Long companyId;
    private String taxYear;
    private Integer period;
    private LocalDateTime processedAt;
}
