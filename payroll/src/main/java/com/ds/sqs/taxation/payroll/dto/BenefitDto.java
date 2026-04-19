package com.ds.sqs.taxation.payroll.dto;

import lombok.Data;

@Data
public class BenefitDto {

    private Integer id;
    private String taxYear;
    private String code;
    private String irp5Code;
    private String name;
    private Boolean taxable;
}
