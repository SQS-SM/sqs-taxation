package com.ds.sqs.taxation.common.dto;

import jakarta.validation.constraints.NotNull;

public record CountryDto(
        @NotNull Long id,
        String code,
        String name
) {}
