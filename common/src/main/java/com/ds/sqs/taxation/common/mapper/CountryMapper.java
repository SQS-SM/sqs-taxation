package com.ds.sqs.taxation.common.mapper;

import com.ds.sqs.taxation.common.dto.CountryDto;
import com.ds.sqs.taxation.common.entity.Country;
import org.springframework.stereotype.Component;

@Component
public class CountryMapper {


    public CountryDto toDto(Country domain) {
        if ( domain == null ) {
            return null;
        }

        Long id = null;
        String code = null;
        String name = null;

        id = domain.getId();
        code = domain.getCode();
        name = domain.getName();

        CountryDto countryDto = new CountryDto( id, code, name );

        return countryDto;
    }


    public Country toEntity(CountryDto domain) {
        if ( domain == null ) {
            return null;
        }

        Country country = new Country();

        country.setId( domain.id() );
        country.setCode( domain.code() );
        country.setName( domain.name() );

        return country;
    }
}

