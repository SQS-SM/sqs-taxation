package com.ds.sqs.taxation.common.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Standard API error response")
public class ErrorResponse {

    @Schema(example = "500")
    private int status;

    @Schema(example = "Internal Server Error")
    private String error;

    @Schema(example = "Unexpected error occurred")
    private String message;

    @Schema(example = "2026-03-15T12:20:00")
    private String timestamp;

}