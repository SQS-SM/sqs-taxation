package com.ds.sqs.taxation.common.dto;

import lombok.Data;

@Data
public class ProvinceDto {

    private Long id;
    private String code;
    private String name;

    private Long countryId;
    private String countryName;
}
