package com.ds.sqs.taxation.common.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BankDetailsDto {

    @NotNull
    private Long id;
    private String bankName;
    private String accountHolderName;
    private String accountType;
    private String branchCode;
    private String branchName;
    private String accountNumber;
}