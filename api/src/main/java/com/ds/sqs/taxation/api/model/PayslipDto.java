package com.ds.sqs.taxation.api.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class PayslipDto {

    private String employeeName;
    private String employeeCode;
    private String jobTitle;
    private String payPeriod;
    private BigDecimal grossSalary;
    private BigDecimal netPay;

    private List<LineItem> earnings;
    private List<LineItem> deductions;

    @Data
    @Builder
    public static class LineItem {
        private String description;
        private BigDecimal amount;
    }
}
