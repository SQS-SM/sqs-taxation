package com.ds.sqs.taxation.common.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProvinceDto {

    @NotNull
    private Long id;
    private String code;
    private String name;

    private Long countryId;
    private String countryName;
}
