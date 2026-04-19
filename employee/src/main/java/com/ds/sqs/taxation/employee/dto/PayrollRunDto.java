package com.ds.sqs.taxation.employee.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PayrollRunDto {

    @NotNull
    private Long id;
    private Long companyId;
    private String taxYear;
    private Integer period;
    private LocalDateTime processedAt;
}
