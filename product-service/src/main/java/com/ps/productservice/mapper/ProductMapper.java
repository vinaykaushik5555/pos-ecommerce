package com.ps.productservice.mapper;

import com.ps.productservice.dto.CreateProductRequest;
import com.ps.productservice.dto.ProductResponse;
import com.ps.productservice.dto.UpdateProductRequest;
import com.ps.productservice.enums.EventType;
import com.ps.productservice.event.ProductCreatedEvent;
import com.ps.productservice.event.ProductUpdatedEvent;
import com.ps.productservice.model.Category;
import com.ps.productservice.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toEntity(CreateProductRequest dto);

    Product toEntity(UpdateProductRequest dto);

    void updateEntity(UpdateProductRequest dto, @MappingTarget Product product);

    ProductResponse toDto(Product entity);

    default ProductCreatedEvent toEvent(CreateProductRequest request, String correlationId) {
        ProductCreatedEvent event = new ProductCreatedEvent();
        event.setCorrelationId(correlationId);
        event.setCreateProductRequest(request);
        event.setEventType(EventType.PRODUCT_CREATED);
        return event;
    }

    default ProductUpdatedEvent toEvent(UpdateProductRequest request, String correlationId) {
        ProductUpdatedEvent event = new ProductUpdatedEvent();
        event.setCorrelationId(correlationId);
        event.setUpdateProductRequest(request);
        event.setEventType(EventType.PRODUCT_UPDATED);
        return event;
    }

    default Product toEntity(ProductCreatedEvent event) {
        return toEntity(event.getCreateProductRequest());
    }

    default Product toEntity(ProductUpdatedEvent event) {
        return toEntity(event.getUpdateProductRequest());
    }
}