package com.ds.sqs.taxation.payroll.entity;


import com.ds.sqs.taxation.payroll.enums.AllowanceCode;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "allowance_type", schema = "sars")
@Getter
@Setter
public class AllowanceType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private AllowanceCode allowanceCode;

    @Column(nullable = false, unique = true, name = "irp5_code")
    private String irp5Code;

    @Column(nullable = false)
    private String description;
}
