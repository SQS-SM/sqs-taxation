package com.ds.sqs.taxation.security.mapper;

import com.ds.sqs.taxation.security.dto.AccountDto;
import com.ds.sqs.taxation.security.entity.Account;
import com.ds.sqs.taxation.security.entity.Company;
import com.ds.sqs.taxation.security.entity.Organisation;
import com.ds.sqs.taxation.security.entity.Role;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public AccountDto toDto(Account account) {
        if ( account == null ) {
            return null;
        }

        AccountDto accountDto = new AccountDto();

        accountDto.setRoleId( accountRoleId( account ) );
        accountDto.setCompanyId( accountCompanyId( account ) );
        accountDto.setOrganisationId( accountOrganisationId( account ) );
        accountDto.setId( account.getId() );
        accountDto.setFirstName( account.getFirstName() );
        accountDto.setLastName( account.getLastName() );
        accountDto.setEmailAddress( account.getEmailAddress() );
        accountDto.setUsername( account.getUsername() );
        accountDto.setIsFirstTimeLogin( account.getIsFirstTimeLogin() );
        accountDto.setCreateDate( account.getCreateDate() );
        accountDto.setDisableDate( account.getDisableDate() );
        accountDto.setGuid( account.getGuid() );

        return accountDto;
    }

    public Account toEntity(AccountDto dto) {
        if ( dto == null ) {
            return null;
        }

        Account account = new Account();

        account.setId( dto.getId() );
        account.setFirstName( dto.getFirstName() );
        account.setLastName( dto.getLastName() );
        account.setEmailAddress( dto.getEmailAddress() );
        account.setUsername( dto.getUsername() );
        account.setIsFirstTimeLogin( dto.getIsFirstTimeLogin() );
        account.setCreateDate( dto.getCreateDate() );
        account.setDisableDate( dto.getDisableDate() );
        account.setGuid( dto.getGuid() );

        return account;
    }

    private Long accountRoleId(Account account) {
        if ( account == null ) {
            return null;
        }
        Role role = account.getRole();
        if ( role == null ) {
            return null;
        }
        Long id = role.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long accountCompanyId(Account account) {
        if ( account == null ) {
            return null;
        }
        Company company = account.getCompany();
        if ( company == null ) {
            return null;
        }
        Long id = company.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long accountOrganisationId(Account account) {
        if ( account == null ) {
            return null;
        }
        Organisation organisation = account.getOrganisation();
        if ( organisation == null ) {
            return null;
        }
        Long id = organisation.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
