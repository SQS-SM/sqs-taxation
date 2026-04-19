package com.ds.sqs.taxation.employee.entity;

import com.ds.sqs.taxation.common.entity.Address;
import com.ds.sqs.taxation.common.entity.AuditableEntity;
import com.ds.sqs.taxation.common.entity.BankDetails;
import com.ds.sqs.taxation.security.entity.Company;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "employee",
        uniqueConstraints = @UniqueConstraint(columnNames = {"company_id", "employee_number"}))
public class Employee extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID guid;

    @ManyToOne(optional = false)
    @JoinColumn(name = "company_id")
    private Company company;

    @Column(nullable = false)
    private String employeeNumber;

    private String firstName;
    private String lastName;
    private String employmentType;

    @Column(precision = 15, scale = 2, nullable = false)
    private BigDecimal basicSalary;

    @ManyToOne
    @JoinColumn(name = "bank_details_id")
    private BankDetails bankDetails;

    @ManyToOne
    @JoinColumn(name = "physical_address_id")
    private Address physicalAddress;

    @ManyToOne
    @JoinColumn(name = "postal_address_id")
    private Address postalAddress;

    @PrePersist
    void generateGuid() {
        if (guid == null) {
            guid = UUID.randomUUID();
        }
    }

    // getters and setters
}
