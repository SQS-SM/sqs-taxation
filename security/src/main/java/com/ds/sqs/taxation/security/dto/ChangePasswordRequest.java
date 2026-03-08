package com.ds.sqs.taxation.security.dto;

import lombok.Data;

@Data
public class ChangePasswordRequest {

    private Long accountId;
    private String currentPassword;
    private String newPassword;
}
