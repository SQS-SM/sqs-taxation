package com.ds.sqs.taxation.api.model;

import java.math.BigDecimal;
import java.util.List;

public class SampleData {

    public static PayslipDto buildSample(String employeeCode) {
        return PayslipDto.builder()
                .employeeName("Mr Q Molefe")
                .employeeCode(employeeCode)
                .jobTitle("Software Engineer")
                .payPeriod("2025/04/30")
                .netPay(new BigDecimal("78514.64"))
                .earnings(List.of(
                        new PayslipDto.LineItem("Basic Salary", new BigDecimal("124000.00"))
                ))
                .deductions(List.of(
                        new PayslipDto.LineItem("PAYE", new BigDecimal("40256.36")),
                        new PayslipDto.LineItem("UIF", new BigDecimal("177.12"))
                ))
                .build();
    }
}

