package com.ds.sqs.taxation.payroll.entity;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

@Getter
@Setter
@Entity
@Table(
        schema = "sars",
        name = "benefit",
        uniqueConstraints = @UniqueConstraint(name = "uk_benefit_code", columnNames = "code")
)
public class Benefit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "tax_year", nullable = false, length = 4)
    private String taxYear;

    @Column(nullable = false, length = 30)
    private String code;

    @Column(name = "irp5_code", nullable = false, length = 10)
    private String irp5Code;

    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @Column(nullable = false)
    private Boolean taxable = true;

    @Column(name = "contribution_employee", precision = 15, scale = 2)
    private java.math.BigDecimal contributionEmployee;

    @Column(name = "contribution_employer", precision = 15, scale = 2)
    private java.math.BigDecimal contributionEmployer;

    @Column(precision = 15, scale = 2)
    private java.math.BigDecimal cap;

    @Column(columnDefinition = "text")
    private String description;

    // getters/setters
}
