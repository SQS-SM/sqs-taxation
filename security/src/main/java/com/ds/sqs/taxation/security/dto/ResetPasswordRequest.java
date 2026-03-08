package com.ds.sqs.taxation.security.dto;

import lombok.Data;

@Data
public class ResetPasswordRequest {

    private String resetKey;
    private String newPassword;
}
