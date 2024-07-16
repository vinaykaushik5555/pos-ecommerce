package com.ps.productservice.mapper;

import com.ps.productservice.dto.InventoryDTO;
import com.ps.productservice.model.Inventory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InventoryMapper {
    InventoryDTO toDto(Inventory entity);
    Inventory toEntity(InventoryDTO dto);
}