package com.ds.sqs.taxation.common.mapper;

import com.ds.sqs.taxation.common.dto.BankDetailsDto;
import com.ds.sqs.taxation.common.entity.BankDetails;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BankDetailsMapper {

    BankDetailsDto toDto(BankDetails entity);

    BankDetails toEntity(BankDetailsDto dto);
}
