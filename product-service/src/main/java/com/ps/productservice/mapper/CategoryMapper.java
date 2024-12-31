package com.ps.productservice.mapper;

import com.ps.productservice.dto.CategoryDTO;
import com.ps.productservice.model.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDTO toDto(Category entity);

    Category toEntity(CategoryDTO dto);
}
