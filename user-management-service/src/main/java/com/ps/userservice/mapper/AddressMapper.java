package com.ps.userservice.mapper;


import com.ps.userservice.dto.AddressRequest;
import com.ps.userservice.dto.AddressResponse;
import com.ps.userservice.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressResponse toAddressResponse(Address address);

    Address toAddress(AddressRequest request);

    void updateAddressFromRequest(AddressRequest request, @MappingTarget Address address);
}
