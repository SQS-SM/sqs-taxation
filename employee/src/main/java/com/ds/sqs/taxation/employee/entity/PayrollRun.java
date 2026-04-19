package com.ds.sqs.taxation.employee.entity;
import com.ds.sqs.taxation.security.entity.Company;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "payroll_run")
public class PayrollRun {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "company_id")
    private Company company;

    private String taxYear;
    private Integer period;
    private LocalDateTime processedAt;

    @PrePersist
    void prePersist() {
        processedAt = LocalDateTime.now();
    }

    // getters and setters
}
