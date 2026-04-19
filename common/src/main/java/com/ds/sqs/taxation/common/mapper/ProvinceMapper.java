package com.ds.sqs.taxation.common.mapper;

import com.ds.sqs.taxation.common.dto.ProvinceDto;
import com.ds.sqs.taxation.common.entity.Country;
import com.ds.sqs.taxation.common.entity.Province;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ProvinceMapper {

    @Mapping(source = "country.id", target = "countryId")
    @Mapping(source = "country.name", target = "countryName")
    ProvinceDto toDto(Province entity);

    @Mapping(target = "country", source = "countryId", qualifiedByName = "mapCountry")
    Province toEntity(ProvinceDto dto);

    @Named("mapCountry")
    default Country mapCountry(Long id) {
        if (id == null) {
            return null;
        }
        Country country = new Country();
        country.setId(id);
        return country;
    }
}
