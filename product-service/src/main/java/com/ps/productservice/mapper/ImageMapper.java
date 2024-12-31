package com.ps.productservice.mapper;

import com.ps.productservice.dto.ImageDTO;
import com.ps.productservice.model.Image;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImageMapper {
    ImageDTO toDto(Image entity);
    Image toEntity(ImageDTO dto);
}