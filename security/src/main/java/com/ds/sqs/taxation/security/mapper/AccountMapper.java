package com.ds.sqs.taxation.security.mapper;

import com.ds.sqs.taxation.security.dto.AccountDto;
import com.ds.sqs.taxation.security.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(source = "role.id", target = "roleId")
    @Mapping(source = "company.id", target = "companyId")
    @Mapping(source = "organisation.id", target = "organisationId")
    AccountDto toDto(Account account);

    @Mapping(target = "role", ignore = true)
    @Mapping(target = "company", ignore = true)
    @Mapping(target = "organisation", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "passwordSalt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    Account toEntity(AccountDto dto);
}
