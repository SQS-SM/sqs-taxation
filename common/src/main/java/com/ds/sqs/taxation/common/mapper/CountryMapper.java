package com.ds.sqs.taxation.common.mapper;

import com.ds.sqs.taxation.common.dto.CountryDto;
import com.ds.sqs.taxation.common.entity.Country;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CountryMapper {
    CountryDto toDto(Country domain);

    Country toEntity(CountryDto domain);
}
