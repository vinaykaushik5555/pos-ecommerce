package com.ps.productservice.mapper;
import com.ps.productservice.dto.VariantDTO;
import com.ps.productservice.model.Variant;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VariantMapper {
    VariantDTO toDto(Variant entity);
    Variant toEntity(VariantDTO dto);
}
