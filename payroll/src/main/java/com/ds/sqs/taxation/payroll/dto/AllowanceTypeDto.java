package com.ds.sqs.taxation.payroll.dto;

import com.ds.sqs.taxation.payroll.enums.AllowanceCode;
import lombok.Data;

@Data
public class AllowanceTypeDto {

    private Integer id;
    private AllowanceCode allowanceCode;
    private String irp5Code;
    private String description;
}