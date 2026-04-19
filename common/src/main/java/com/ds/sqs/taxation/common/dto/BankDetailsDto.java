package com.ds.sqs.taxation.common.dto;

import lombok.Data;

@Data
public class BankDetailsDto {

    private Long id;
    private String bankName;
    private String accountHolderName;
    private String accountType;
    private String branchCode;
    private String branchName;
    private String accountNumber;
}