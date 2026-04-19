package com.ds.sqs.taxation.payroll.dto;

import com.ds.sqs.taxation.payroll.enums.AllowanceCode;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AllowanceTypeDto {

    @NotNull
    private Integer id;
    private AllowanceCode allowanceCode;
    private String irp5Code;
    private String description;
}