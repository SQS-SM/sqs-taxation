package com.ds.sqs.taxation.common.mapper;

import com.ds.sqs.taxation.common.dto.ProvinceDto;
import com.ds.sqs.taxation.common.entity.Country;
import com.ds.sqs.taxation.common.entity.Province;
import org.springframework.stereotype.Component;

@Component
public class ProvinceMapper {

    public ProvinceDto toDto(Province entity) {
        if (entity == null) {
            return null;
        }

        ProvinceDto provinceDto = new ProvinceDto();

        provinceDto.setCountryId(entityCountryId(entity));
        provinceDto.setCountryName(entityCountryName(entity));
        provinceDto.setId(entity.getId());
        provinceDto.setCode(entity.getCode());
        provinceDto.setName(entity.getName());

        return provinceDto;
    }


    public Province toEntity(ProvinceDto dto) {
        if (dto == null) {
            return null;
        }

        Province province = new Province();

        province.setCountry(mapCountry(dto.getCountryId()));
        province.setId(dto.getId());
        province.setCode(dto.getCode());
        province.setName(dto.getName());

        return province;
    }

    private Long entityCountryId(Province province) {
        if (province == null) {
            return null;
        }
        Country country = province.getCountry();
        if (country == null) {
            return null;
        }
        Long id = country.getId();
        if (id == null) {
            return null;
        }
        return id;
    }

    private String entityCountryName(Province province) {
        if (province == null) {
            return null;
        }
        Country country = province.getCountry();
        if (country == null) {
            return null;
        }
        String name = country.getName();
        if (name == null) {
            return null;
        }
        return name;
    }

    private Country mapCountry(Long id) {
        if (id == null) {
            return null;
        }
        Country country = new Country();
        country.setId(id);
        return country;
    }
}
