package com.ds.sqs.taxation.common.dto;

import lombok.Data;

@Data
public class AddressDto {

    private Long id;
    private String line1;
    private String line2;
    private String suburb;
    private String city;
    private String postalCode;
    private Long provinceId;
    private String provinceName;
}
