package com.ds.sqs.taxation.common.mapper;

import com.ds.sqs.taxation.common.dto.AddressDto;
import com.ds.sqs.taxation.common.entity.Address;
import com.ds.sqs.taxation.common.entity.Province;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    @Mapping(source = "province.id", target = "provinceId")
    @Mapping(source = "province.name", target = "provinceName")
    AddressDto toDto(Address address);

    @Mapping(target = "province", source = "provinceId", qualifiedByName = "mapProvince")
    Address toEntity(AddressDto dto);

    @Named("mapProvince")
    default Province mapProvince(Long provinceId) {
        if (provinceId == null) {
            return null;
        }
        Province province = new Province();
        province.setId(provinceId);
        return province;
    }
}
