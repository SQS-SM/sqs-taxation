package com.ds.sqs.taxation.employee.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "employee_payroll_result")
public class EmployeePayrollResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "payroll_run_id")
    private PayrollRun payrollRun;

    @ManyToOne(optional = false)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(precision = 15, scale = 2)
    private BigDecimal grossSalary;

    @Column(precision = 15, scale = 2)
    private BigDecimal taxableIncome;

    @Column(precision = 15, scale = 2)
    private BigDecimal paye;

    @Column(precision = 15, scale = 2)
    private BigDecimal uif;

    @Column(precision = 15, scale = 2)
    private BigDecimal sdl;

    @Column(precision = 15, scale = 2)
    private BigDecimal netSalary;

    // getters and setters
}
