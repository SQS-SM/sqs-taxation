package com.ds.sqs.taxation.common.mapper;

import com.ds.sqs.taxation.common.dto.AddressDto;
import com.ds.sqs.taxation.common.entity.Address;
import com.ds.sqs.taxation.common.entity.Province;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    public AddressDto toDto(Address address) {
        if ( address == null ) {
            return null;
        }

        AddressDto addressDto = new AddressDto();

        addressDto.setProvinceId( addressProvinceId( address ) );
        addressDto.setProvinceName( addressProvinceName( address ) );
        addressDto.setId( address.getId() );
        addressDto.setLine1( address.getLine1() );
        addressDto.setLine2( address.getLine2() );
        addressDto.setSuburb( address.getSuburb() );
        addressDto.setCity( address.getCity() );
        addressDto.setPostalCode( address.getPostalCode() );

        return addressDto;
    }


    public Address toEntity(AddressDto dto) {
        if ( dto == null ) {
            return null;
        }

        Address address = new Address();

        address.setProvince( mapProvince( dto.getProvinceId() ) );
        address.setId( dto.getId() );
        address.setLine1( dto.getLine1() );
        address.setLine2( dto.getLine2() );
        address.setSuburb( dto.getSuburb() );
        address.setCity( dto.getCity() );
        address.setPostalCode( dto.getPostalCode() );

        return address;
    }

    private Long addressProvinceId(Address address) {
        if ( address == null ) {
            return null;
        }
        Province province = address.getProvince();
        if ( province == null ) {
            return null;
        }
        Long id = province.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String addressProvinceName(Address address) {
        if ( address == null ) {
            return null;
        }
        Province province = address.getProvince();
        if ( province == null ) {
            return null;
        }
        String name = province.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
    private Province mapProvince(Long provinceId) {
        if (provinceId == null) {
            return null;
        }
        Province province = new Province();
        province.setId(provinceId);
        return province;
    }
}

