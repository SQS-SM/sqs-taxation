package com.ds.sqs.taxation.common.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "bank_details")
public class BankDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bankName;
    private String accountHolderName;
    private String accountType;
    private String branchCode;
    private String branchName;
    private String accountNumber;
}